package ru.geekbrains.weatherapp.common.listener;

public interface Subject {

    void registerObservers();

    void removeObservers();

    void notifyObserver();
}
