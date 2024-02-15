package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * IPokemonFactoryTest
 */
public class IPokemonFactoryTest {
    IPokemonFactory iPokemonFactory;

    @Before
    public void init() {
        iPokemonFactory = Mockito.mock(IPokemonFactory.class);

        Mockito.when(iPokemonFactory.createPokemon(0, 613, 64, 4000, 4))
            .thenReturn(new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56));
        
        Mockito.when(iPokemonFactory.createPokemon(133, 2729, 202, 5000, 4))
            .thenReturn(new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100));
    }

    @Test
    public void testCreatePokemon() {
        Pokemon pokemon = iPokemonFactory.createPokemon(0, 613, 64, 4000, 4);
        assertEquals(0, pokemon.getIndex());
        assertEquals(613, pokemon.getCp());
        assertEquals(64, pokemon.getHp());
        assertEquals(4000, pokemon.getDust());
        assertEquals(4, pokemon.getCandy());

        Mockito.verify(iPokemonFactory).createPokemon(0, 613, 64, 4000, 4);
        
        pokemon = iPokemonFactory.createPokemon(133, 2729, 202, 5000, 4);
        assertEquals(133, pokemon.getIndex());
        assertEquals(2729, pokemon.getCp());
        assertEquals(202, pokemon.getHp());
        assertEquals(5000, pokemon.getDust());
        assertEquals(4, pokemon.getCandy());

        Mockito.verify(iPokemonFactory).createPokemon(133, 2729, 202, 5000, 4);
    }
}