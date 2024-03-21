package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * IPokedexTest
 */
public class IPokedexTest {

    IPokedex pokedex;
    List<Pokemon> listPokemons;
    
    @Before 
    public void init() throws PokedexException {
        pokedex = Mockito.mock(IPokedex.class);
        listPokemons = new ArrayList<Pokemon>();

        Pokemon Bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 0.56);
        Pokemon Aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 1.0);
        
        Mockito.when(pokedex.size()).thenAnswer(
            new Answer<Integer>() {
                public Integer answer(InvocationOnMock invocationOnMock) {
                    return Integer.valueOf(listPokemons.size());
                }
            }
        );

        Mockito.when(pokedex.addPokemon(Mockito.any(Pokemon.class))).thenAnswer(
            new Answer<Integer>() {
                public Integer answer(InvocationOnMock invocationOnMock) throws PokedexException{
                    Pokemon pokemon = invocationOnMock.getArgument(0);
                    
                    if (pokemon.getIndex() < 0) {
                        throw new PokedexException("The given Pokemon's Id is less than 0");
                    } 
                    if (pokemon.getIndex() > 150 ) {
                        throw new PokedexException("The given Pokemon's Id is greater than 150");
                    }

                    listPokemons.add(pokemon);
                    return Integer.valueOf(pokemon.getIndex());
                }
            }
        );

        Mockito.when(pokedex.getPokemon(Mockito.anyInt())).thenAnswer(
            new Answer<Pokemon>() {
                public Pokemon answer(InvocationOnMock invocationOnMock) throws Throwable {
                    int pokemonId = invocationOnMock.getArgument(0);

                    if (pokemonId < 0) {
                        throw new PokedexException("The given Pokemon Id is less than 0");
                    } 
                    if (pokemonId > 150 ) {
                        throw new PokedexException("The given Pokemon Id is greater than 150");
                    }

                    switch (pokemonId) {
                        case 0:
                            return Bulbizarre;
                        case 133:
                            return Aquali;
                        default:
                            return null;
                    }
                }
            }
        );
        
        Mockito.when(pokedex.getPokemons()).thenReturn(listPokemons);
        Mockito.when(pokedex.getPokemons(Mockito.any(PokemonComparators.class))).thenAnswer(
            new Answer<List<Pokemon>>() {
                public List<Pokemon> answer(InvocationOnMock invocationOnMock) {
                    PokemonComparators comparator = invocationOnMock.getArgument(0);
                    listPokemons.sort(comparator);
                    return listPokemons;
                }
            }
        );
    }

	@Test
	public void testSize1() {
        assertEquals(0, pokedex.size());
        
        Mockito.verify(pokedex).size();
	}

    @Test
    public void testSIze2() {
        Pokemon Bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 0.56);
        Pokemon Aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 1.0);
        Pokemon Missino = new Pokemon(-1, "Aquali", 0, 0, 0, 0, 0, 0, 0, 1.0);
        pokedex.addPokemon(Bulbizarre);
        pokedex.addPokemon(Aquali);
        assertThrows(PokedexException.class, () -> {pokedex.addPokemon(Missino);});
        
        assertEquals(2, pokedex.size());

        Mockito.verify(pokedex).size();
    }

	@Test
	public void testAddPokemon() {
        Pokemon Bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 0.56);
		assertEquals(0, pokedex.addPokemon(Bulbizarre));
        Mockito.verify(pokedex).addPokemon(Bulbizarre);
	}

	@Test
	public void testGetPokemon() throws PokedexException {
		Pokemon pokemon = pokedex.getPokemon(0);
        assertEquals(0, pokemon.getIndex());
        assertEquals("Bulbizarre", pokemon.getName());
        assertEquals(126, pokemon.getAttack());
        assertEquals(126, pokemon.getDefense());
        assertEquals(90, pokemon.getStamina());
        assertEquals(613, pokemon.getCp());
        assertEquals(64, pokemon.getHp());
        assertEquals(4000, pokemon.getDust());
        assertEquals(4, pokemon.getCandy());
        assertEquals(0.56, pokemon.getIv(), 0.01);

        assertThrows(PokedexException.class, () -> {pokedex.getPokemon(-1);});

        Mockito.verify(pokedex).getPokemon(0);
	}

	@Test
	public void testGetPokemons() {
        Pokemon Bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 0.56);
        Pokemon Aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 1.0);
        
        pokedex.addPokemon(Bulbizarre);
        pokedex.addPokemon(Aquali);

		List<Pokemon> listPokemons = pokedex.getPokemons();
        assertEquals(0, listPokemons.get(0).getIndex());
        assertEquals("Bulbizarre", listPokemons.get(0).getName());
        assertEquals(126, listPokemons.get(0).getAttack());
        assertEquals(126, listPokemons.get(0).getDefense());
        assertEquals(90, listPokemons.get(0).getStamina());
        assertEquals(613, listPokemons.get(0).getCp());
        assertEquals(64, listPokemons.get(0).getHp());
        assertEquals(4000, listPokemons.get(0).getDust());
        assertEquals(4, listPokemons.get(0).getCandy());
        assertEquals(0.56, listPokemons.get(0).getIv(), 0.01);

        assertEquals(133, listPokemons.get(1).getIndex());
        assertEquals("Aquali", listPokemons.get(1).getName());
        assertEquals(186, listPokemons.get(1).getAttack());
        assertEquals(168, listPokemons.get(1).getDefense());
        assertEquals(260, listPokemons.get(1).getStamina());
        assertEquals(2729, listPokemons.get(1).getCp());
        assertEquals(202, listPokemons.get(1).getHp());
        assertEquals(5000, listPokemons.get(1).getDust());
        assertEquals(4, listPokemons.get(1).getCandy());
        assertEquals(1.0, listPokemons.get(1).getIv(), 0.01);

        Mockito.verify(pokedex).getPokemons();
	}

	@Test
	public void testGetPokemons2() {
        Pokemon Bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 0.56);
        Pokemon Aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 1.0);
        
        pokedex.addPokemon(Bulbizarre);
        pokedex.addPokemon(Aquali);

        List<Pokemon> listPokemons = pokedex.getPokemons(PokemonComparators.CP);
        assertEquals(0, listPokemons.get(0).getIndex());
        assertEquals("Bulbizarre", listPokemons.get(0).getName());
        assertEquals(126, listPokemons.get(0).getAttack());
        assertEquals(126, listPokemons.get(0).getDefense());
        assertEquals(90, listPokemons.get(0).getStamina());
        assertEquals(613, listPokemons.get(0).getCp());
        assertEquals(64, listPokemons.get(0).getHp());
        assertEquals(4000, listPokemons.get(0).getDust());
        assertEquals(4, listPokemons.get(0).getCandy());
        assertEquals(0.56, listPokemons.get(0).getIv(), 0.01);

        assertEquals(133, listPokemons.get(1).getIndex());
        assertEquals("Aquali", listPokemons.get(1).getName());
        assertEquals(186, listPokemons.get(1).getAttack());
        assertEquals(168, listPokemons.get(1).getDefense());
        assertEquals(260, listPokemons.get(1).getStamina());
        assertEquals(2729, listPokemons.get(1).getCp());
        assertEquals(202, listPokemons.get(1).getHp());
        assertEquals(5000, listPokemons.get(1).getDust());
        assertEquals(4, listPokemons.get(1).getCandy());
        assertEquals(1.0, listPokemons.get(1).getIv(), 0.01);

        Mockito.verify(pokedex).getPokemons(PokemonComparators.CP);

        listPokemons = pokedex.getPokemons(PokemonComparators.INDEX);
        assertEquals(0, listPokemons.get(0).getIndex());
        assertEquals("Bulbizarre", listPokemons.get(0).getName());
        assertEquals(126, listPokemons.get(0).getAttack());
        assertEquals(126, listPokemons.get(0).getDefense());
        assertEquals(90, listPokemons.get(0).getStamina());
        assertEquals(613, listPokemons.get(0).getCp());
        assertEquals(64, listPokemons.get(0).getHp());
        assertEquals(4000, listPokemons.get(0).getDust());
        assertEquals(4, listPokemons.get(0).getCandy());
        assertEquals(0.56, listPokemons.get(0).getIv(), 0.01);

        assertEquals(133, listPokemons.get(1).getIndex());
        assertEquals("Aquali", listPokemons.get(1).getName());
        assertEquals(186, listPokemons.get(1).getAttack());
        assertEquals(168, listPokemons.get(1).getDefense());
        assertEquals(260, listPokemons.get(1).getStamina());
        assertEquals(2729, listPokemons.get(1).getCp());
        assertEquals(202, listPokemons.get(1).getHp());
        assertEquals(5000, listPokemons.get(1).getDust());
        assertEquals(4, listPokemons.get(1).getCandy());
        assertEquals(1.0, listPokemons.get(1).getIv(), 0.01);

        Mockito.verify(pokedex).getPokemons(PokemonComparators.INDEX);
        
        listPokemons = pokedex.getPokemons(PokemonComparators.NAME);
        assertEquals(133, listPokemons.get(0).getIndex());
        assertEquals("Aquali", listPokemons.get(0).getName());
        assertEquals(186, listPokemons.get(0).getAttack());
        assertEquals(168, listPokemons.get(0).getDefense());
        assertEquals(260, listPokemons.get(0).getStamina());
        assertEquals(2729, listPokemons.get(0).getCp());
        assertEquals(202, listPokemons.get(0).getHp());
        assertEquals(5000, listPokemons.get(0).getDust());
        assertEquals(4, listPokemons.get(0).getCandy());
        assertEquals(1.0, listPokemons.get(0).getIv(), 0.01);

        assertEquals(0, listPokemons.get(1).getIndex());
        assertEquals("Bulbizarre", listPokemons.get(1).getName());
        assertEquals(126, listPokemons.get(1).getAttack());
        assertEquals(126, listPokemons.get(1).getDefense());
        assertEquals(90, listPokemons.get(1).getStamina());
        assertEquals(613, listPokemons.get(1).getCp());
        assertEquals(64, listPokemons.get(1).getHp());
        assertEquals(4000, listPokemons.get(1).getDust());
        assertEquals(4, listPokemons.get(1).getCandy());
        assertEquals(0.56, listPokemons.get(1).getIv(), 0.01);

        Mockito.verify(pokedex).getPokemons(PokemonComparators.NAME);
	}
}