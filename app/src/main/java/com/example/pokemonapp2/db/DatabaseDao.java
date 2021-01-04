package com.example.pokemonapp2.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pokemonapp2.network.Pokemon;

import java.util.List;

@Dao
public interface DatabaseDao {

    @Insert
    public void insertPokemon(Pokemon pokemon);

    @Query("delete from pokemon_table where name =:pokemonName")
    public void deletePokemon(String pokemonName);

    @Query("select * from pokemon_table")
    LiveData<List<Pokemon>> getListPokemon();

}
