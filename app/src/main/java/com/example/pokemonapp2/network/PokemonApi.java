package com.example.pokemonapp2.network;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface PokemonApi {
    @GET("pokemon")
    Observable<PokemonResponse> getPokemons();
}
