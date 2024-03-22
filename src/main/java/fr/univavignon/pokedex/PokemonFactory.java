package fr.univavignon.pokedex;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonMetadata;

/**
 * PokemonFactory
 */
public class PokemonFactory implements IPokemonFactory {
    static PokemonMetadataProvider metadataProvider;

    public PokemonFactory(PokemonMetadataProvider metadataProvider) {
        PokemonFactory.metadataProvider = metadataProvider;
    }

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        try {
            PokemonMetadata metadata = metadataProvider.getPokemonMetadata(index);

            index = metadata.getIndex();
            String name = metadata.getName();
            int attack = metadata.getAttack();
            int defense = metadata.getDefense();
            int stamina = metadata.getStamina();

            Pokemon pokemon = new Pokemon(index, name, attack, defense, stamina, cp, hp, dust, candy, 0);

            return pokemon;
        } catch (PokedexException e) {
            e.printStackTrace();
        }

        return null;
    }
}