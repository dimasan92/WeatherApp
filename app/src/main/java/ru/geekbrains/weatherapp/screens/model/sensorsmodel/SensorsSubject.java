package ru.geekbrains.weatherapp.screens.model.sensorsmodel;

public interface SensorsSubject {

    void registerSensorsObserver(SensorsObserver o);

    void removeSensorsObserver(SensorsObserver o);

    void notifySensorsObservers();
}
