package ru.geekbrains.weatherapp.model.weathermodel;

public interface IWeatherData {
    void request(String city);

    int getCod();

    float getTemp();

    int getPressure();

    int getHumidity();

    float getWindSpeed();

    int getClouds();

    String getName();
}
