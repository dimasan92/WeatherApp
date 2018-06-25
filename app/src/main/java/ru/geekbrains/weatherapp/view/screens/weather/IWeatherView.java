package ru.geekbrains.weatherapp.view.screens.weather;

import ru.geekbrains.weatherapp.view.IView;

public interface IWeatherView extends IView {

    void setCity(String cityName);

    void setVisibilityWindParam(boolean visible);

    void setVisibilityPressureParam(boolean visible);

    void setVisibilityHumidityParam(boolean visible);

    void setDate(String date);

    void setDayOfWeek(String day);

    void setPressure(String value);

    void setHumidity(String value);

    void setWind(String value);

    void startWeatherService();

    void registerReceivers();

    void unregisterReceivers();
}
