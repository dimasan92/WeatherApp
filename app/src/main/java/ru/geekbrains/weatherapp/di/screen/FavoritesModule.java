package ru.geekbrains.weatherapp.di.screen;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import ru.geekbrains.weatherapp.api.WeatherApi;
import ru.geekbrains.weatherapp.database.CitiesDao;
import ru.geekbrains.weatherapp.database.CitiesEntity;
import ru.geekbrains.weatherapp.ui.screens.favorites.contracts.IFavoritesModel;
import ru.geekbrains.weatherapp.ui.screens.favorites.contracts.IFavoritesPresenter;
import ru.geekbrains.weatherapp.ui.screens.favorites.contracts.IFavoritesView;
import ru.geekbrains.weatherapp.ui.screens.favorites.implementations.FavoritesModel;
import ru.geekbrains.weatherapp.ui.screens.favorites.implementations.FavoritesPresenter;
import ru.geekbrains.weatherapp.ui.screens.favorites.list.CitiesAdapter;
import ru.geekbrains.weatherapp.ui.screens.favorites.list.NetCityAdapter;

@Module
public class FavoritesModule {

    @ScreenScope
    @Provides
    IFavoritesPresenter<IFavoritesView> providePresenter(IFavoritesModel model) {
        CompositeDisposable disposables = new CompositeDisposable();
        return new FavoritesPresenter<>(model, disposables);
    }

    @ScreenScope
    @Provides
    IFavoritesModel provideModel(CitiesDao dao, WeatherApi api) {
        return new FavoritesModel(dao, api);
    }

    @ScreenScope
    @Provides
    CitiesAdapter provideDBAdapter(){
        List<CitiesEntity> emptyList = new ArrayList<>();
        return new CitiesAdapter(emptyList);
    }

    @ScreenScope
    @Provides
    NetCityAdapter provideNetAdapter(){
        List<CitiesEntity> emptyList = new ArrayList<>();
        return new NetCityAdapter(emptyList);
    }
}
