package ru.geekbrains.weatherapp.view.dialogs.settings;

import android.content.Intent;

import ru.geekbrains.weatherapp.view.IView;

public interface ISettingsDialog extends IView {

    boolean getPressureParam();

    boolean getWindParam();

    boolean getHumidityParam();

    void sendResult(int resultCode, Intent intent);
}
