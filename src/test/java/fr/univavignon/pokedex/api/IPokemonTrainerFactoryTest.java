package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

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
        trainerFactory = Mockito.mock(IPokemonTrainerFactory.class);
        pokedexFactory = Mockito.mock(IPokedexFactory.class);

        metadataProvider = Mockito.mock(IPokemonMetadataProvider.class);
        pokemonFactory = Mockito.mock(IPokemonFactory.class);

        IPokedex pokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

        Mockito.when(trainerFactory.createTrainer("James", Team.MYSTIC, pokedexFactory)).thenReturn(
            new PokemonTrainer("James", Team.MYSTIC, pokedex));
    }

    @Test
    public void testCreateTrainer() {
        PokemonTrainer trainer = trainerFactory.createTrainer("James", Team.MYSTIC, pokedexFactory);

        assertEquals("James", trainer.getName());
        assertEquals(Team.MYSTIC, trainer.getTeam());
        assertEquals(pokedexFactory.createPokedex(metadataProvider, pokemonFactory), trainer.getPokedex());

        Mockito.verify(trainerFactory).createTrainer("James", Team.MYSTIC, pokedexFactory);
    }
}