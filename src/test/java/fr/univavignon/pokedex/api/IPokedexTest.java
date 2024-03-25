package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import fr.univavignon.pokedex.Pokedex;
import fr.univavignon.pokedex.PokemonFactory;
import fr.univavignon.pokedex.PokemonMetadataProvider;

/**
 * IPokedexTest
 */
public class IPokedexTest {
    IPokemonMetadataProvider metadataProvider;
    IPokemonFactory pokemonFactory;
    IPokedex pokedex;
    List<Pokemon> listPokemons;

    @Before
    public void init() throws PokedexException, MalformedURLException, IOException {
        metadataProvider = new PokemonMetadataProvider();
        pokemonFactory = new PokemonFactory(metadataProvider);
        pokedex = new Pokedex(metadataProvider, pokemonFactory);
    }

    @Test
    public void testCreatePokemon() {
        Pokemon pokemon = pokedex.createPokemon(0, 1, 2, 3, 4);

        assertEquals(0, pokemon.getIndex());
        assertEquals(1, pokemon.getCp());
        assertEquals(2, pokemon.getHp());
        assertEquals(3, pokemon.getDust());
        assertEquals(4, pokemon.getCandy());
    }

    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        Pokemon Bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 0.56);
        pokedex.addPokemon(Bulbizarre);

        PokemonMetadata metadata = pokedex.getPokemonMetadata(0);

        // values are not the same because the api's values are not the same
        assertEquals(1, metadata.getIndex());
        assertEquals("Bulbasaur", metadata.getName());
        assertEquals(118, metadata.getAttack());
        assertEquals(111, metadata.getDefense());
        assertEquals(128, metadata.getStamina());
    }

    @Test
    public void testSize() {
        assertEquals(0, pokedex.size());

        Pokemon Bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 0.56);
        Pokemon Aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 1.0);

        pokedex.addPokemon(Bulbizarre);
        pokedex.addPokemon(Aquali);

        assertEquals(2, pokedex.size());
    }

    @Test
    public void testAddPokemon() {
        Pokemon Bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 0.56);

        assertEquals(0, pokedex.addPokemon(Bulbizarre));
    }

    @Test
    public void testGetPokemon() throws PokedexException {
        Pokemon Bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 0.56);
        pokedex.addPokemon(Bulbizarre);

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

        assertThrows(PokedexException.class, () -> {
            pokedex.getPokemon(-1);
        });
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

        // using cp comparator
        listPokemons = pokedex.getPokemons(PokemonComparators.CP);
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

        // using index comparator
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

        // using name comparator
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
    }
}
