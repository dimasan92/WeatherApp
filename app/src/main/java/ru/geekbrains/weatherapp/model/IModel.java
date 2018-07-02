package ru.geekbrains.weatherapp.model;

import ru.geekbrains.weatherapp.model.citiesmodel.CitiesSubject;
import ru.geekbrains.weatherapp.model.citiesmodel.ICitiesData;
import ru.geekbrains.weatherapp.model.sensorsmodel.ISensorsData;
import ru.geekbrains.weatherapp.model.sensorsmodel.SensorsSubject;

public interface IModel {

    ICitiesData cities();

    ISensorsData sensors();

    CitiesSubject citiesSubject();

    SensorsSubject sensorsSubject();
}
