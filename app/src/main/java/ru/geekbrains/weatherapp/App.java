package ru.geekbrains.weatherapp;

import android.app.Application;

import ru.geekbrains.weatherapp.di.application.AppComponent;
import ru.geekbrains.weatherapp.di.application.DaggerAppComponent;
import ru.geekbrains.weatherapp.di.application.DatabaseModule;
import ru.geekbrains.weatherapp.di.screen.ScreenComponent;
import ru.geekbrains.weatherapp.di.screen.SettingsModule;

public class App extends Application {

    private static App instance;

    private AppComponent mAppComponent;
    private ScreenComponent mScreenComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mAppComponent = DaggerAppComponent
                .builder()
                .databaseModule(new DatabaseModule(getApplicationContext()))
                .build();
    }

    public static App getApp() {
        return instance;
    }

    public ScreenComponent getScreenComponent() {
        if (mScreenComponent == null) {
            mScreenComponent = mAppComponent
                    .getScreenComponent(new SettingsModule(getApplicationContext()));
        }
        return mScreenComponent;
    }

    public void clearScreenComponent() {
        mScreenComponent = null;
    }
}
