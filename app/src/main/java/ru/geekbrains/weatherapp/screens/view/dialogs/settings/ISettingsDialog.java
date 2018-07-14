package ru.geekbrains.weatherapp.screens.view.dialogs.settings;

import ru.geekbrains.weatherapp.screens.base.IView;

public interface ISettingsDialog extends IView {

    void setWindParam(boolean paramIsSet);

    void setPressureParam(boolean paramIsSet);

    void setHumidityParam(boolean paramIsSet);

    boolean getPressureParam();

    boolean getWindParam();

    boolean getHumidityParam();
}
