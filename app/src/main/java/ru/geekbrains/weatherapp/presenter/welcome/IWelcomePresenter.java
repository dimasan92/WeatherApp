package ru.geekbrains.weatherapp.presenter.welcome;

import ru.geekbrains.weatherapp.presenter.IPresenter;

public interface IWelcomePresenter extends IPresenter {

    void onNewCityClick();

    void viewIsReady();

    void onListItemClick(String cityName);

    void onMenuItemSettingsClick();
}
