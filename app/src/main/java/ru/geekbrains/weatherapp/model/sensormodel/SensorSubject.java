package ru.geekbrains.weatherapp.model.sensormodel;

public interface SensorSubject {

    void registerSensorObserver(SensorObserver o);

    void removeSensorObserver(SensorObserver o);

    void notifySensorObservers();
}
