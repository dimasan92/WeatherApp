package ru.geekbrains.weatherapp.view.dialogs.settings;

import android.content.Intent;

import ru.geekbrains.weatherapp.view.IView;

public interface ISettingsDialog extends IView {

    void setWindParam(boolean paramIsSet);

    void setPressureParam(boolean paramIsSet);

    void setHumidityParam(boolean paramIsSet);

    boolean getPressureParam();

    boolean getWindParam();

    boolean getHumidityParam();
}
