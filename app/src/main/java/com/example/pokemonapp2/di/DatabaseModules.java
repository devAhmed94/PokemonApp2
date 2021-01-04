package com.example.pokemonapp2.di;

import android.app.Application;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pokemonapp2.db.DatabaseDao;
import com.example.pokemonapp2.db.DatabaseRoom;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DatabaseModules {

    @Provides
    @Singleton
    public DatabaseRoom providesDatabase(Application application){
        return Room.databaseBuilder(application, DatabaseRoom.class,"fav_database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    public DatabaseDao provideDao(DatabaseRoom databaseRoom)
    {
        return databaseRoom.databaseDao();
    }
}
