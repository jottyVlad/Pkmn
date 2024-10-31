package ru.mirea.pkmn.bardatskiyvi.web.http;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;


/**
 * An HTTP client for interacting with the Pokémon TCG API.
 * It uses Retrofit to make API requests and handles JSON responses.
 */
public class PkmnHttpClient {
    Retrofit client;
    PokemonTcgAPI tcgAPI;


    /**
     * Constructs a new PkmnHttpClient, initializing the Retrofit client and API interface.
     */
    public PkmnHttpClient() {
        client = new Retrofit.Builder()
                .baseUrl("https://api.pokemontcg.io")
                .addConverterFactory(JacksonConverterFactory.create(new JsonMapper()))
                .build();

        tcgAPI = client.create(PokemonTcgAPI.class);
    }

    /**
     * Retrieves Pokémon card data from the Pokémon TCG API based on the provided name and number.
     *
     * @param name   The name of the Pokémon card.
     * @param number The number of the Pokémon card.
     * @return A JsonNode representing the API response containing card data.
     * @throws IOException If there is an error communicating with the API.
     */
    public JsonNode getPokemonCard(String name, String number) throws IOException {
        String query = "name:\""+ name + "\"" + " " + "number:" + number;

        Response<JsonNode> response = tcgAPI.getPokemon(query).execute();
        return response.body();
    }
}
