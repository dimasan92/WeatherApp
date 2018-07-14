package ru.geekbrains.weatherapp.screens.model.weathermodel;

public interface IWeatherData {
    void request(String city);

    void setWeatherListener(WeatherListener listener);
}
