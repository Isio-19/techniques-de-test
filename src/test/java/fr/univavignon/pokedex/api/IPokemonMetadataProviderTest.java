package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * IPokemonMetadataProviderTest
 */
public class IPokemonMetadataProviderTest {
    IPokemonMetadataProvider pokemonMetadataProvider;

    @Before
    public void init() throws PokedexException {
        pokemonMetadataProvider = Mockito.mock(IPokemonMetadataProvider.class); 

        
        Mockito.when(pokemonMetadataProvider.getPokemonMetadata(Mockito.anyInt())).thenAnswer(
            new Answer<PokemonMetadata>() {
                public PokemonMetadata answer(InvocationOnMock invocationOnMock) throws Throwable {
                    int pokemonId = invocationOnMock.getArgument(0);

                    if (pokemonId < 0) {
                        throw new PokedexException("The given Pokemon Id is less than 0");
                    } 
                    if (pokemonId > 150 ) {
                        throw new PokedexException("The given Pokemon Id is greater than 150");
                    }

                    switch (pokemonId) {
                        case 0:
                            return new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
                        case 133:
                            return new PokemonMetadata(133, "Aquali", 186, 168, 260);
                        default:
                            return null;
                    }
                }
            }
        );
    }   

    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        PokemonMetadata metadata = pokemonMetadataProvider.getPokemonMetadata(0);
        
        assertEquals(0, metadata.getIndex());
        assertEquals("Bulbizarre", metadata.getName());
        assertEquals(126, metadata.getAttack());
        assertEquals(126, metadata.getDefense());
        assertEquals(90, metadata.getStamina());

        Mockito.verify(pokemonMetadataProvider).getPokemonMetadata(0);
        
        metadata = pokemonMetadataProvider.getPokemonMetadata(133);
        
        assertEquals(133, metadata.getIndex());
        assertEquals("Aquali", metadata.getName());
        assertEquals(186, metadata.getAttack());
        assertEquals(168, metadata.getDefense());
        assertEquals(260, metadata.getStamina());

        Mockito.verify(pokemonMetadataProvider).getPokemonMetadata(133);

        assertThrows(PokedexException.class, () -> {pokemonMetadataProvider.getPokemonMetadata(-1);});
    }
}