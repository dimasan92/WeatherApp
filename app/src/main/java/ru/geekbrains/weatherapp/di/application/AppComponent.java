package ru.geekbrains.weatherapp.di.application;

import javax.inject.Singleton;

import dagger.Component;
import ru.geekbrains.weatherapp.di.screen.ScreenComponent;

@Singleton
@Component(modules = {DatabaseModule.class, WeatherApiModule.class, NetModule.class})
public interface AppComponent {

    ScreenComponent getScreenComponent();
}
