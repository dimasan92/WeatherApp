package ru.geekbrains.weatherapp.model.sensorsmodel;

public interface SensorsSubject {

    void registerSensorsObserver(SensorsObserver o);

    void removeSensorsObserver(SensorsObserver o);

    void notifySensorsObservers();
}
