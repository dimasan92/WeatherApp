package ru.geekbrains.weatherapp.screens.welcome.contracts;

import ru.geekbrains.weatherapp.screens.base.IPresenter;
import ru.geekbrains.weatherapp.screens.base.IView;

public interface IWelcomePresenter<T extends IView> extends IPresenter<T> {

    void viewIsReady();

//    void onNewCityClick();
//
//    void onListItemClick(String cityName);
//
//    void onMenuItemSettingsClick();
}
