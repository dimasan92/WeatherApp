package ru.geekbrains.weatherapp.application.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.geekbrains.weatherapp.database.CitiesDao;

@Singleton
@Component(modules = {DatabaseModule.class})
public interface AppComponent {

    CitiesDao citiesDao();
}
