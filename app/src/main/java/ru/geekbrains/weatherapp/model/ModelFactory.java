package ru.geekbrains.weatherapp.model;

import android.content.Context;
import android.support.annotation.NonNull;

import ru.geekbrains.weatherapp.model.citiesmodel.CitiesData;
import ru.geekbrains.weatherapp.model.citiesmodel.ICitiesData;
import ru.geekbrains.weatherapp.model.sensorsmodel.ISensorsData;
import ru.geekbrains.weatherapp.model.sensorsmodel.SensorsData;
import ru.geekbrains.weatherapp.model.weathermodel.IWeatherData;
import ru.geekbrains.weatherapp.model.weathermodel.WeatherData;

class ModelFactory {

    static ICitiesData getCities(@NonNull Context context) {
        return new CitiesData(context);
    }

    static ISensorsData getSensors(@NonNull Context context) {
        return new SensorsData(context);
    }

    static IWeatherData getWeather() {
        return new WeatherData();
    }
}
