package ru.geekbrains.weatherapp.model.settingsmodel;

public interface ISettingsData {

    void saveIndState(boolean wind, boolean pressure, boolean humidity);

    boolean getParamWind();

    boolean getParamPressure();

    boolean getParamHumidity();
}
