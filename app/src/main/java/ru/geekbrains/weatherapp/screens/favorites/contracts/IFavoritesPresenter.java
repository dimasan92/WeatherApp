package ru.geekbrains.weatherapp.screens.favorites.contracts;

import ru.geekbrains.weatherapp.screens.base.IPresenter;
import ru.geekbrains.weatherapp.screens.base.IView;

public interface IFavoritesPresenter<T extends IView> extends IPresenter<T> {

    void viewIsReady();

//    void onNewCityClick();
//
//    void onListItemClick(String cityName);
//
//    void onMenuItemSettingsClick();
}
