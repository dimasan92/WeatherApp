package ru.geekbrains.weatherapp.model.datamodel;

public interface DataSubject {

    void registerDataObserver(DataObserver o);

    void removeDataObserver(DataObserver o);

    void notifyDataObservers();
}
