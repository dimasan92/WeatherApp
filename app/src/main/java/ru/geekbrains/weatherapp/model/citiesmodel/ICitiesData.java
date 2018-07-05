package ru.geekbrains.weatherapp.model.citiesmodel;

import java.util.List;

public interface ICitiesData {

    boolean addCity(String city);

    List<String> getCities();
}
