package ru.geekbrains.weatherapp.di.application;

import javax.inject.Singleton;

import dagger.Component;
import ru.geekbrains.weatherapp.database.CitiesDao;
import ru.geekbrains.weatherapp.di.screen.ScreenComponent;

@Singleton
@Component(modules = {DatabaseModule.class})
public interface AppComponent {

    ScreenComponent getScreenComponent();
}
