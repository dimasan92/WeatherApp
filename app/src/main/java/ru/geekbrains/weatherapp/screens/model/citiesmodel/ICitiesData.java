package ru.geekbrains.weatherapp.screens.model.citiesmodel;

import java.util.List;

public interface ICitiesData {

    boolean addCity(String city);

    List<String> getCities();
}
