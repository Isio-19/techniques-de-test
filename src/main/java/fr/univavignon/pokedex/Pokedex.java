package fr.univavignon.pokedex;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonMetadata;

/**
 * Implementation of the IPokedex interface
 */
public class Pokedex implements IPokedex {
    IPokemonFactory factory;
    IPokemonMetadataProvider provider;
    List<Pokemon> pokemonList;

    /**
     * Default constructor
     * 
     * @param metadataProvider Metadata factory used to get the metadata for Pokemon instance
     * @param pokemonFactory Pokemon factory used to build the Pokemon instance to return
     */    
    public Pokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        provider = metadataProvider;
        factory = pokemonFactory;
        pokemonList = new ArrayList<Pokemon>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        return provider.getPokemonMetadata(index);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        return factory.createPokemon(index, cp, hp, dust, candy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return pokemonList.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int addPokemon(Pokemon pokemon) {
        pokemonList.add(pokemon);
        return pokemon.getIndex();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pokemon getPokemon(int id) throws PokedexException {
        for (int i = 0; i < pokemonList.size(); i++) {
            if (pokemonList.get(i).getIndex() == id)
                return pokemonList.get(i);
        }

        throw new PokedexException("The pokemon with the given index isn't in the pokedex");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pokemon> getPokemons() {
        List<Pokemon> returnList = new ArrayList<Pokemon>(pokemonList);
        return returnList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
        List<Pokemon> returnList = new ArrayList<Pokemon>(pokemonList);
        returnList.sort(order);
        return returnList;
    }
}
