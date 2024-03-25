package fr.univavignon.pokedex;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.IPokedexFactory;
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;

/**
 * Implementation of the IPokedexFactory interface
 */
public class PokedexFactory implements IPokedexFactory {
    /**
     * Default constructor
     */
    public PokedexFactory() {}

    /**
     * {@inheritDoc}
     */
    @Override
    public IPokedex createPokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        return new Pokedex(metadataProvider, pokemonFactory);
    }
}
