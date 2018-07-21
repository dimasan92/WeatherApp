package ru.geekbrains.weatherapp.di.application;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.database.CitiesDao;
import ru.geekbrains.weatherapp.database.CitiesDatabase;

@Module
public class DatabaseModule {

    private CitiesDatabase mDatabase;

    public DatabaseModule(Context context){
        mDatabase = Room
                .databaseBuilder(context, CitiesDatabase.class, Constants.DB_CITIES_NAME)
                .build();
    }

    @Singleton
    @Provides
    CitiesDao provideCitiesDao(){
        return  mDatabase.citiesDAO();
    }
}
