package com.example.pokemonapp2.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.pokemonapp2.network.Pokemon;

@Database(entities = Pokemon.class,version = 1,exportSchema = false)
public abstract class DatabaseRoom extends RoomDatabase {
    public abstract DatabaseDao  databaseDao();
}
