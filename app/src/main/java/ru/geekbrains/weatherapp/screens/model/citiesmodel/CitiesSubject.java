package ru.geekbrains.weatherapp.screens.model.citiesmodel;

public interface CitiesSubject {

    void regCitiesObserver(CitiesObserver o);

    void unregCitiesObserver(CitiesObserver o);

    void notifyCitiesObservers();
}
