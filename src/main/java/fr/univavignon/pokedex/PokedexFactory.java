package fr.univavignon.pokedex;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.IPokedexFactory;
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;

public class PokedexFactory implements IPokedexFactory {
    PokemonMetadataProvider metadataProvider;
    PokemonFactory pokemonFactory;


    @Override
    public IPokedex createPokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        try {
            if (this.metadataProvider == null)
                this.metadataProvider = (PokemonMetadataProvider) metadataProvider;

            if (this.pokemonFactory == null)
                this.pokemonFactory = (PokemonFactory) pokemonFactory;

            return new Pokedex(this.metadataProvider, this.pokemonFactory);
        } catch (Exception e) {
        }

        return null;
    }
    
}
