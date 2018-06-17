package ru.geekbrains.weatherapp.model;

import java.util.Set;

public interface DataModel {

    boolean addCity(String cityName);

    Set<String> getCities();
}
