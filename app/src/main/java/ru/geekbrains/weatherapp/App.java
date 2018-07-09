package ru.geekbrains.weatherapp;

import android.app.Application;
import android.arch.persistence.room.Room;

import ru.geekbrains.weatherapp.model.database.CitiesDao;
import ru.geekbrains.weatherapp.model.database.CitiesDatabase;

public class App extends Application {

    private static final String DATABASE = "database";

    public static App instance;

    private CitiesDao mDao;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }

    public CitiesDao getDao() {
        if(mDao == null){
            CitiesDatabase database = Room.databaseBuilder(this, CitiesDatabase.class, DATABASE)
                    .allowMainThreadQueries()
                    .build();
            mDao = database.citiesDAO();
        }
        return mDao;
    }
}
