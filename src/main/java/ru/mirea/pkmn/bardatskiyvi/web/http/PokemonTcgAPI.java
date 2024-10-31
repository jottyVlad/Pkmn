package ru.mirea.pkmn.bardatskiyvi.web.http;

import com.fasterxml.jackson.databind.JsonNode;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Retrofit interface for interacting with the Pokémon TCG API.
 */
public interface PokemonTcgAPI {

    /**
     * Performs a GET request to retrieve Pokémon card data based on the provided query.
     *
     * @param query The search query for the API request.
     * @return A Call object representing the asynchronous API request.
     *         The response body will contain a JsonNode with card data.
     */
    @GET("/v2/cards")
    Call<JsonNode> getPokemon(@Query(value="q", encoded = true) String query);
}
