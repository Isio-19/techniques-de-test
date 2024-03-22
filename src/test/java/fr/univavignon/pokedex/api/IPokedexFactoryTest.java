package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class IPokedexFactoryTest {
    IPokedexFactory iPokedexFactory;
    IPokemonMetadataProvider metadataProvider;
    IPokemonFactory pokemonFactory;
    IPokedex iPokedex;

    @Before
    public void init() {
        iPokedexFactory = Mockito.mock(IPokedexFactory.class);
        metadataProvider = Mockito.mock(IPokemonMetadataProvider.class);
        pokemonFactory = Mockito.mock(IPokemonFactory.class);

        Mockito.when(iPokedexFactory.createPokedex(metadataProvider, pokemonFactory)).thenReturn(Mockito.mock(IPokedex.class));
    }

    @Test
    public void testCreatePokedex() {
        IPokedex iPokedexTest = iPokedexFactory.createPokedex(metadataProvider, pokemonFactory);
        assertTrue(iPokedexTest instanceof IPokedex);
        Mockito.verify(iPokedexFactory).createPokedex(metadataProvider, pokemonFactory);
    }
}
