package ru.geekbrains.weatherapp.screens.model;

import ru.geekbrains.weatherapp.screens.model.citiesmodel.CitiesSubject;
import ru.geekbrains.weatherapp.screens.model.citiesmodel.ICitiesData;
import ru.geekbrains.weatherapp.screens.model.sensorsmodel.ISensorsData;
import ru.geekbrains.weatherapp.screens.model.sensorsmodel.SensorsSubject;
import ru.geekbrains.weatherapp.screens.model.weathermodel.IWeatherData;

public interface IModel {

    ICitiesData cities();

    ISensorsData sensors();

    IWeatherData weather();

    CitiesSubject citiesSubject();

    SensorsSubject sensorsSubject();
}
