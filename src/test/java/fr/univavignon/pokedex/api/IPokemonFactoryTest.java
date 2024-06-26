package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import fr.univavignon.pokedex.PokemonFactory;
import fr.univavignon.pokedex.PokemonMetadataProvider;
import fr.univavignon.pokedex.RocketPokemonFactory;

/**
 * IPokemonFactoryTest
 */
public class IPokemonFactoryTest {
    IPokemonFactory iPokemonFactory;
    IPokemonMetadataProvider metadataProvider;
    RocketPokemonFactory rocketPokemonFactory;

    @Before
    public void init() throws PokedexException {
        metadataProvider = Mockito.mock(PokemonMetadataProvider.class);
        iPokemonFactory = new PokemonFactory(metadataProvider);

        Mockito.when(metadataProvider.getPokemonMetadata(Mockito.anyInt())).thenAnswer(
                new Answer<PokemonMetadata>() {
                    public PokemonMetadata answer(InvocationOnMock invocationOnMock) throws Throwable {
                        int pokemonId = invocationOnMock.getArgument(0);

                        if (pokemonId < 0) {
                            throw new PokedexException("The given Pokemon Id is less than 0");
                        }
                        if (pokemonId > 150) {
                            throw new PokedexException("The given Pokemon Id is greater than 150");
                        }

                        switch (pokemonId) {
                            case 0:
                                return new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
                            case 133:
                                return new PokemonMetadata(133, "Aquali", 186, 168, 260);
                            default:
                                return new PokemonMetadata(pokemonId, "PokemonSansNom", 0, 0, 0);
                        }
                    }
                });

        rocketPokemonFactory = new RocketPokemonFactory();
    }

    @Test
    public void testCreatePokemon() {
        Pokemon pokemon = iPokemonFactory.createPokemon(0, 613, 64, 4000, 4);
        assertEquals(0, pokemon.getIndex());
        assertEquals(126, pokemon.getAttack());
        assertEquals(126, pokemon.getDefense());
        assertEquals(90, pokemon.getStamina());
        assertEquals(613, pokemon.getCp());
        assertEquals(64, pokemon.getHp());
        assertEquals(4000, pokemon.getDust());
        assertEquals(4, pokemon.getCandy());

        pokemon = iPokemonFactory.createPokemon(133, 2729, 202, 5000, 4);
        assertEquals(133, pokemon.getIndex());
        assertEquals(186, pokemon.getAttack());
        assertEquals(168, pokemon.getDefense());
        assertEquals(260, pokemon.getStamina());
        assertEquals(2729, pokemon.getCp());
        assertEquals(202, pokemon.getHp());
        assertEquals(5000, pokemon.getDust());
        assertEquals(4, pokemon.getCandy());

        pokemon = iPokemonFactory.createPokemon(-1, 0, 0, 0, 0);
        assertEquals(null, pokemon);
    }

    @Test
    public void testPokemonStats() {
        Pokemon pokemon = rocketPokemonFactory.createPokemon(0, 613, 64, 4000, 4);
        assertEquals(0, pokemon.getIndex());
        assertEquals(126, pokemon.getAttack());
        assertEquals(126, pokemon.getDefense());
        assertEquals(90, pokemon.getStamina());
        assertEquals(613, pokemon.getCp());
        assertEquals(64, pokemon.getHp());
        assertEquals(4000, pokemon.getDust());
        assertEquals(4, pokemon.getCandy());
    }

    @Test
    public void testEqualPokemonStats() {
        Pokemon pokemon1 = rocketPokemonFactory.createPokemon(0, 613, 64, 4000, 4);
        Pokemon pokemon2 = rocketPokemonFactory.createPokemon(0, 613, 64, 4000, 4);

        assertEquals(pokemon1.getIndex(), pokemon2.getIndex());
        assertEquals(pokemon1.getAttack(), pokemon2.getAttack());
        assertEquals(pokemon1.getDefense(), pokemon2.getDefense());
        assertEquals(pokemon1.getStamina(), pokemon2.getStamina());
        assertEquals(pokemon1.getCp(), pokemon2.getCp());
        assertEquals(pokemon1.getHp(), pokemon2.getHp());
        assertEquals(pokemon1.getDust(), pokemon2.getDust());
        assertEquals(pokemon1.getCandy(), pokemon2.getCandy());
    }

    @Test
    public void testLowerIdBound() {
        Pokemon pokemon = rocketPokemonFactory.createPokemon(-1, 0, 0, 0, 0);
        assertEquals(null, pokemon);
    }

    @Test
    public void testUpperIdBound() {
        Pokemon pokemon = rocketPokemonFactory.createPokemon(151, 0, 0, 0, 0);
        assertEquals(null, pokemon);
    }
}