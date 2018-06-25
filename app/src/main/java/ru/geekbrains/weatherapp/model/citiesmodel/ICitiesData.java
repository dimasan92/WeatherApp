package ru.geekbrains.weatherapp.model.citiesmodel;

import java.util.Set;

public interface ICitiesData {

    boolean addCity(String city);

    Set<String> getCities();
}
