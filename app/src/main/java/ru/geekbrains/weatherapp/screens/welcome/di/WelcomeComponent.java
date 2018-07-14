package ru.geekbrains.weatherapp.screens.welcome.di;

import dagger.Component;
import ru.geekbrains.weatherapp.application.di.AppComponent;
import ru.geekbrains.weatherapp.screens.welcome.implementations.WelcomeView;

@WelcomeScope
@Component(dependencies = {AppComponent.class}, modules = {WelcomeModule.class})
public interface WelcomeComponent {

    void inject(WelcomeView view);
}
