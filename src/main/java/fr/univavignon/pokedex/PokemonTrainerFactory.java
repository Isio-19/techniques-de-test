package fr.univavignon.pokedex;

import java.io.IOException;

import fr.univavignon.pokedex.api.IPokedexFactory;
import fr.univavignon.pokedex.api.IPokemonTrainerFactory;
import fr.univavignon.pokedex.api.PokemonTrainer;
import fr.univavignon.pokedex.api.Team;

public class PokemonTrainerFactory implements IPokemonTrainerFactory {
    PokedexFactory pokedexFactory;
    PokemonMetadataProvider provider;
    PokemonFactory pokemonFactory;

    public PokemonTrainerFactory(PokedexFactory pokedexFactory, PokemonMetadataProvider provider, PokemonFactory pokemonFactory) {
        this.pokedexFactory = pokedexFactory;
        this.provider = provider;
        this.pokemonFactory = pokemonFactory; 
    }

    @Override
    public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {
        Pokedex pokedex = (Pokedex) pokedexFactory.createPokedex(provider, pokemonFactory);
        
        return new PokemonTrainer(name, team, pokedex);
    }
}
