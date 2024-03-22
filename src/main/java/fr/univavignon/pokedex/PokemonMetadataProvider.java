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

public class PokemonMetadataProvider implements IPokemonMetadataProvider {
    JSONArray pokemonMetadata;

    public PokemonMetadataProvider() throws IOException {
        URL url;
        try {
            url = new URL("https://pogoapi.net/api/v1/pokemon_stats.json");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null)
                content.append(inputLine);

            in.close();

            pokemonMetadata = new JSONArray(content.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        if (index < 0 || index > 150) {
            throw new PokedexException("Given Pokemon ID is out of range.");
        }

        JSONObject pokemon = pokemonMetadata.getJSONObject(index);

        String name = pokemon.getString("pokemon_name");
        int attack = pokemon.getInt("base_attack");
        int defense = pokemon.getInt("base_defense");
        int stamina = pokemon.getInt("base_stamina");

        return new PokemonMetadata(index, name, attack, defense, stamina);
    }
}
