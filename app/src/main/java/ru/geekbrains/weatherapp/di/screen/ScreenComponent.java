package ru.geekbrains.weatherapp.di.screen;

import dagger.Subcomponent;
import ru.geekbrains.weatherapp.ui.screens.favorites.implementations.FavoritesView;
import ru.geekbrains.weatherapp.ui.screens.main.implementations.MainView;
import ru.geekbrains.weatherapp.ui.screens.settings.implementations.SettingsView;

@ScreenScope
@Subcomponent(modules = {MainModule.class, FavoritesModule.class, SettingsModule.class})
public interface ScreenComponent {

    void inject(MainView view);

    void inject(FavoritesView view);

    void inject(SettingsView view);
}
