package ru.geekbrains.weatherapp.model.listener;

public interface DataSubject {

    void registerDataObserver(DataObserver o);

    void removeDataObserver(DataObserver o);

    void notifyDataObservers();
}
