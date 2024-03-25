package fr.univavignon.pokedex;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.IPokedexFactory;
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.IPokemonTrainerFactory;
import fr.univavignon.pokedex.api.PokemonTrainer;
import fr.univavignon.pokedex.api.Team;

/**
 * Implementation of the IPokemonTrainerFactory interface
 */
public class PokemonTrainerFactory implements IPokemonTrainerFactory {
    IPokedexFactory pokedexFactory;
    IPokemonMetadataProvider metadataProvider;
    IPokemonFactory pokemonFactory;
    IPokedex pokedex;

    /**
     * Default constructor
     * 
     * @param pokedexFactory Pokedex factory to use for creating associated pokedex instance.
     * @param metadataProvider Metadata provider to use for creating pokemons in the pokedex instance.
     * @param pokemonFactory Pokemon factory to use for creating pokemons in the pokedex instance.
     */
    public PokemonTrainerFactory(IPokedexFactory pokedexFactory, IPokemonMetadataProvider metadataProvider,
            IPokemonFactory pokemonFactory) {
        this.pokedexFactory = pokedexFactory;
        this.metadataProvider = metadataProvider;
        this.pokemonFactory = pokemonFactory;
        pokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {
        return new PokemonTrainer(name, team, pokedex);
    }
}
