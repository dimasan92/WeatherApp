package ru.geekbrains.weatherapp.application;

import android.app.Application;
import android.arch.persistence.room.Room;

import ru.geekbrains.weatherapp.application.di.AppComponent;
import ru.geekbrains.weatherapp.application.di.DaggerAppComponent;
import ru.geekbrains.weatherapp.application.di.DatabaseModule;
import ru.geekbrains.weatherapp.database.CitiesDao;
import ru.geekbrains.weatherapp.database.CitiesDatabase;

public class App extends Application {

    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sAppComponent = DaggerAppComponent
                .builder()
                .databaseModule(new DatabaseModule(getApplicationContext()))
                .build();
    }

    public static AppComponent getAppComponent(){
        return sAppComponent;
    }
}
