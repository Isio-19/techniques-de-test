package fr.univavignon.pokedex;

import java.io.IOException;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.IPokedexFactory;
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.IPokemonTrainerFactory;
import fr.univavignon.pokedex.api.PokemonTrainer;
import fr.univavignon.pokedex.api.Team;

public class PokemonTrainerFactory implements IPokemonTrainerFactory {
    IPokedexFactory pokedexFactory;
    IPokemonMetadataProvider metadataProvider;
    IPokemonFactory pokemonFactory;
    IPokedex pokedex;

    public PokemonTrainerFactory(IPokedexFactory pokedexFactory, IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        this.pokedexFactory = pokedexFactory;
        this.metadataProvider = metadataProvider;
        this.pokemonFactory = pokemonFactory; 
        pokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);
    }

    @Override
    public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {
        return new PokemonTrainer(name, team, pokedex);
    }
}
