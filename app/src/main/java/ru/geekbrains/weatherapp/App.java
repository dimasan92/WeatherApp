package ru.geekbrains.weatherapp;

import android.app.Application;
import android.arch.persistence.room.Room;

import ru.geekbrains.weatherapp.model.database.CitiesDatabase;

public class App extends Application {

    private static final String DATABASE = "database";

    public static App instance;

    private CitiesDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, CitiesDatabase.class, DATABASE)
                .allowMainThreadQueries()
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public CitiesDatabase getDatabase() {
        return database;
    }
}
