package ru.geekbrains.weatherapp.model.settingsmodel;

public interface ISettingsData {

    void saveIndState(boolean temperature, boolean pressure, boolean humidity);

    boolean getParamWind();

    boolean getParamPressure();

    boolean getParamHumidity();
}
