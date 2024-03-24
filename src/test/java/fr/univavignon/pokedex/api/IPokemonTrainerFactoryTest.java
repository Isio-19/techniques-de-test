package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.univavignon.pokedex.PokemonTrainerFactory;

/**
 * IPokemonTrainerFactoryTest
 */
public class IPokemonTrainerFactoryTest {
    IPokemonTrainerFactory trainerFactory;
    IPokedexFactory pokedexFactory;
    IPokemonMetadataProvider metadataProvider;
    IPokemonFactory pokemonFactory;

    @Before
    public void init() {
        pokedexFactory = Mockito.mock(IPokedexFactory.class);
        metadataProvider = Mockito.mock(IPokemonMetadataProvider.class);
        pokemonFactory = Mockito.mock(IPokemonFactory.class);
        
        trainerFactory = new PokemonTrainerFactory(pokedexFactory, metadataProvider, pokemonFactory);
    }

    @Test
    public void testCreateTrainer() {
        PokemonTrainer trainer = trainerFactory.createTrainer("James", Team.MYSTIC, pokedexFactory);

        assertEquals("James", trainer.getName());
        assertEquals(Team.MYSTIC, trainer.getTeam());
        assertEquals(pokedexFactory.createPokedex(metadataProvider, pokemonFactory), trainer.getPokedex());
    }
}