package ru.geekbrains.weatherapp.view.dialogs.newcity;

import ru.geekbrains.weatherapp.view.IView;

public interface INewCityDialog extends IView {

    String getEnteredText();

    void showError(int stringResource);

    void hideError();

    void makeToast(int stringId);

    void startWeatherService(String city);

    void registerReceiver();

    void unregisterReceiver();

    void close();
}
