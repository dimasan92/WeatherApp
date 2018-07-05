package ru.geekbrains.weatherapp.model.weathermodel;

public interface IWeatherData {
    void request(String city);

    void setWeatherListener(WeatherListener listener);
}
