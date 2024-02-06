package fr.univavignon.pokedex.api;

import org.junit.Test;

/**
 * IPokedexTest
 */
public interface IPokedexTest {

    @Test
    public void testAddPokemon();

    @Test
    public void testGetPokemon();

    @Test
    public void testGetPokemons();

    @Test
    public void testGetPokemons2();

    @Test
    public void testSize();
}