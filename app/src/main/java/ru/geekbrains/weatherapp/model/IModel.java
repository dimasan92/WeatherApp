package ru.geekbrains.weatherapp.model;

import ru.geekbrains.weatherapp.model.citiesmodel.CitiesSubject;
import ru.geekbrains.weatherapp.model.citiesmodel.ICitiesData;
import ru.geekbrains.weatherapp.model.sensorsmodel.ISensorsData;
import ru.geekbrains.weatherapp.model.sensorsmodel.SensorsSubject;
import ru.geekbrains.weatherapp.model.settingsmodel.ISettingsData;
import ru.geekbrains.weatherapp.model.timemodel.ITimeData;

public interface IModel {

    ICitiesData cities();

    ISensorsData sensors();

    ITimeData time();

    ISettingsData settings();

    CitiesSubject citiesSubject();

    SensorsSubject sensorsSubject();
}
