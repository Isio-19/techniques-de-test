package fr.univavignon.pokedex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.PokemonMetadata;

/**
 * Implementation of the IPokemonMetadataProvider interface
 */
public class PokemonMetadataProvider implements IPokemonMetadataProvider {
    JSONArray pokemonMetadata;

    /**
     * Default constructor
     * 
     * @throws IOException Exception thrown if the link to the API doesn't return anything
     * @throws MalformedURLException Exception thrown if the API used to build the Pokemon Metadata provider is invalid
     */
    public PokemonMetadataProvider() throws IOException, MalformedURLException {
        URL url = new URL("https://pogoapi.net/api/v1/pokemon_stats.json");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null)
            content.append(inputLine);

        in.close();

        pokemonMetadata = new JSONArray(content.toString());
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        if (index < 0 || index > 151)
            throw new PokedexException("Given Pokemon ID is out of range.");

        JSONObject pokemon = pokemonMetadata.getJSONObject(index);

        index = pokemon.getInt("pokemon_id");
        String name = pokemon.getString("pokemon_name");
        int attack = pokemon.getInt("base_attack");
        int defense = pokemon.getInt("base_defense");
        int stamina = pokemon.getInt("base_stamina");

        return new PokemonMetadata(index, name, attack, defense, stamina);
    }
}
