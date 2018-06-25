package ru.geekbrains.weatherapp.model.citiesmodel;

public interface CitiesSubject {

    void regCitiesObserver(CitiesObserver o);

    void unregCitiesObserver(CitiesObserver o);

    void notifyCitiesObservers();
}
