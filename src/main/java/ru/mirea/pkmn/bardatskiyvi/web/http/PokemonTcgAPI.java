package ru.mirea.pkmn.bardatskiyvi.web.http;

import com.fasterxml.jackson.databind.JsonNode;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokemonTcgAPI {
    @GET("/v2/cards")
    Call<JsonNode> getPokemon(@Query(value="q", encoded = true) String query);
}
