package ru.geekbrains.weatherapp.model.citiesmodel;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import ru.geekbrains.weatherapp.App;
import ru.geekbrains.weatherapp.model.database.CitiesDao;
import ru.geekbrains.weatherapp.model.database.CitiesDatabase;
import ru.geekbrains.weatherapp.model.database.CitiesEntity;

public class CitiesData implements ICitiesData, CitiesSubject {

    private CitiesDao mCities;

    private List<CitiesObserver> mObservers;

    public CitiesData(@NonNull Context context) {
        mObservers = new ArrayList<>();

        mCities = App.getInstance().getDao();
    }

    @Override
    public boolean addCity(String cityName) {
        mCities.insert(new CitiesEntity(cityName));

        notifyCitiesObservers();
        return true;
    }

    @Override
    public List<String> getCities() {
        List<String> cities = new ArrayList<>();
        for (CitiesEntity entity : mCities.getAll()) {
            cities.add(entity.getName());
        }
        return cities;
    }

    @Override
    public void regCitiesObserver(CitiesObserver o) {
        mObservers.add(o);
    }

    @Override
    public void unregCitiesObserver(CitiesObserver o) {
        mObservers.remove(o);
    }

    @Override
    public void notifyCitiesObservers() {
        for (CitiesObserver o : mObservers) {
            o.updateCities();
        }
    }
}
