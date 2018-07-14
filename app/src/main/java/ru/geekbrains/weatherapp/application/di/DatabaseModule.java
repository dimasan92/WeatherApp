package ru.geekbrains.weatherapp.application.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.weatherapp.database.CitiesDao;
import ru.geekbrains.weatherapp.database.CitiesDatabase;

@Module
public class DatabaseModule {

    private static final String DATABASE = "database";

    private CitiesDatabase mDatabase;

    public DatabaseModule(Context context){
        mDatabase = Room.databaseBuilder(context, CitiesDatabase.class, DATABASE)
                .allowMainThreadQueries()
                .build();
    }

    @Singleton
    @Provides
    CitiesDao provideCitiesDao(){
        return  mDatabase.citiesDAO();
    }
}
