package com.example.pokemonapp2.repository;

import androidx.lifecycle.LiveData;

import com.example.pokemonapp2.db.DatabaseDao;
import com.example.pokemonapp2.network.Pokemon;
import com.example.pokemonapp2.network.PokemonApi;
import com.example.pokemonapp2.network.PokemonResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;


public class PokemonRepository {
    private PokemonApi pokemonApi;
    private DatabaseDao databaseDao;
    @Inject
    public PokemonRepository(PokemonApi pokemonApi , DatabaseDao  databaseDao) {
        this.pokemonApi = pokemonApi;
        this.databaseDao = databaseDao;
    }
   public Observable<PokemonResponse> getPokemons(){
        return pokemonApi.getPokemons();
    }
    public void insertPokemon(Pokemon pokemon){
        this.databaseDao.insertPokemon(pokemon);
    }
    public void deletePokemon(Pokemon pokemon){
        this.databaseDao.deletePokemon(pokemon.getName());
    }
    public LiveData<List<Pokemon>> getListOfPokemon(){
        return this.databaseDao.getListPokemon();
    }
}
