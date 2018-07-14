package ru.geekbrains.weatherapp.screens.presenter.dialog;

import ru.geekbrains.weatherapp.screens.base.IPresenter;

public interface IDialogPresenter extends IPresenter {

    INewCity newCity();

    ISensorsIndications sensorsIndications();

    ISettings settings();
}
