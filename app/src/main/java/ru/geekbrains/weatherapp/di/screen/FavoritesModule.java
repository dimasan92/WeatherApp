package ru.geekbrains.weatherapp.di.screen;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.weatherapp.database.CitiesDao;
import ru.geekbrains.weatherapp.screens.favorites.contracts.IFavoritesModel;
import ru.geekbrains.weatherapp.screens.favorites.contracts.IFavoritesPresenter;
import ru.geekbrains.weatherapp.screens.favorites.contracts.IFavoritesView;
import ru.geekbrains.weatherapp.screens.favorites.implementations.FavoritesModel;
import ru.geekbrains.weatherapp.screens.favorites.implementations.FavoritesPresenter;

@Module
public class FavoritesModule {

    @ScreenScope
    @Provides
    IFavoritesPresenter<IFavoritesView> providePresenter(IFavoritesModel model) {
        return new FavoritesPresenter<>(model);
    }

    @ScreenScope
    @Provides
    IFavoritesModel provideModel(CitiesDao dao) {
        return new FavoritesModel(dao);
    }
}
