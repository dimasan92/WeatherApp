package ru.geekbrains.weatherapp.model.weathermodel;

import ru.geekbrains.weatherapp.model.weathermodel.data.WeatherRequest;

public interface WeatherListener {

    void onSuccess(WeatherRequest request);

    void onFailure(String error);
}
