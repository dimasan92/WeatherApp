package ru.geekbrains.weatherapp.model;

import android.content.Context;
import android.support.annotation.NonNull;

import ru.geekbrains.weatherapp.model.citiesmodel.CitiesData;
import ru.geekbrains.weatherapp.model.citiesmodel.ICitiesData;
import ru.geekbrains.weatherapp.model.sensorsmodel.ISensorsData;
import ru.geekbrains.weatherapp.model.sensorsmodel.SensorsData;
import ru.geekbrains.weatherapp.model.settingsmodel.ISettingsData;
import ru.geekbrains.weatherapp.model.settingsmodel.SettingsData;
import ru.geekbrains.weatherapp.model.timemodel.ITimeData;
import ru.geekbrains.weatherapp.model.timemodel.TimeData;

class ModelFactory {

    static ICitiesData getCities(@NonNull Context context) {
        return new CitiesData(context);
    }

    static ISensorsData getSensors(@NonNull Context context) {
        return new SensorsData(context);
    }

    static ITimeData getTime() {
        return new TimeData();
    }

    static ISettingsData getSettings(@NonNull Context context) {
        return new SettingsData(context);
    }
}
