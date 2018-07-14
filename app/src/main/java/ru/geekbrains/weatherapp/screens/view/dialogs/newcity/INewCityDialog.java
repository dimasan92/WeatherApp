package ru.geekbrains.weatherapp.screens.view.dialogs.newcity;

import ru.geekbrains.weatherapp.screens.base.IView;

public interface INewCityDialog extends IView {

    String getEnteredText();

    void showError(int stringResource);

    void hideError();

    void makeToast(int stringId);

    void close();
}
