package ru.geekbrains.weatherapp.ui.screens.settings.contracts;

import ru.geekbrains.weatherapp.ui.screens.base.IPresenter;
import ru.geekbrains.weatherapp.ui.screens.base.IView;

public interface ISettingsPresenter<T extends IView> extends IPresenter<T> {

    void sensorsSwitchChecked();
}
