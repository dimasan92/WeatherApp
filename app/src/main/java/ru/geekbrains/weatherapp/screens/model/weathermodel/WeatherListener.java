package ru.geekbrains.weatherapp.screens.model.weathermodel;

import ru.geekbrains.weatherapp.model.currentweather.WeatherResponce;

public interface WeatherListener {

    void onSuccess(WeatherResponce request);

    void onFailure(String error);
}
