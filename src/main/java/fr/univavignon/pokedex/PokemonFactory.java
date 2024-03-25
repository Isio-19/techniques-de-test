package fr.univavignon.pokedex;

import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonMetadata;

/**
 * Implementation of the IPokemonFactory interface
 */
public class PokemonFactory implements IPokemonFactory {
    IPokemonMetadataProvider metadataProvider;

    /**
     * Constructor using a PokemonMetadataProvider
     * @param metadataProvider PokemonMetadataProvider
     */
    public PokemonFactory(IPokemonMetadataProvider metadataProvider) {
        this.metadataProvider = metadataProvider;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        try {
            PokemonMetadata metadata = metadataProvider.getPokemonMetadata(index);

            String name = metadata.getName();
            int attack = metadata.getAttack();
            int defense = metadata.getDefense();
            int stamina = metadata.getStamina();

            return new Pokemon(index, name, attack, defense, stamina, cp, hp, dust, candy, 0);
        } catch (PokedexException e) {
            e.printStackTrace();
            return null;
        }
    }
}