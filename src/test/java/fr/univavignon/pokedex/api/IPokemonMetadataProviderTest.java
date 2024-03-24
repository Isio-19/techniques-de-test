package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import fr.univavignon.pokedex.PokemonMetadataProvider;

/**
 * IPokemonMetadataProviderTest
 */
public class IPokemonMetadataProviderTest {
    IPokemonMetadataProvider pokemonMetadataProvider;

    @Before
    public void init() throws PokedexException, IOException {
        pokemonMetadataProvider = new PokemonMetadataProvider(); 
    }   

    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        PokemonMetadata metadata = pokemonMetadataProvider.getPokemonMetadata(0);
        
        // values are not the same because the values of the api does not match the values of the subject
        assertEquals(1, metadata.getIndex());
        assertEquals("Bulbasaur", metadata.getName());
        assertEquals(118, metadata.getAttack());
        assertEquals(111, metadata.getDefense());
        assertEquals(128, metadata.getStamina());

        metadata = pokemonMetadataProvider.getPokemonMetadata(133);
        
        assertEquals(79, metadata.getIndex());
        assertEquals("Slowpoke", metadata.getName());
        assertEquals(109, metadata.getAttack());
        assertEquals(98, metadata.getDefense());
        assertEquals(207, metadata.getStamina());

        assertThrows(PokedexException.class, () -> {pokemonMetadataProvider.getPokemonMetadata(-1);});
    }
}