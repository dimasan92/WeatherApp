package ru.geekbrains.weatherapp.model;

import ru.geekbrains.weatherapp.model.citiesmodel.CitiesSubject;
import ru.geekbrains.weatherapp.model.citiesmodel.ICitiesData;
import ru.geekbrains.weatherapp.model.sensorsmodel.ISensorsData;
import ru.geekbrains.weatherapp.model.sensorsmodel.SensorsSubject;
import ru.geekbrains.weatherapp.model.weathermodel.IWeatherData;

public interface IModel {

    ICitiesData cities();

    ISensorsData sensors();

    IWeatherData weather();

    CitiesSubject citiesSubject();

    SensorsSubject sensorsSubject();
}
