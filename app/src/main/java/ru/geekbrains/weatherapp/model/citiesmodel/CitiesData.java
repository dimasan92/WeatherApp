package ru.geekbrains.weatherapp.model.citiesmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.common.Constants;

//will replace with DB
public class CitiesData implements ICitiesData, CitiesSubject {

    private static final String PREFERENCES_NAME = "list_of_cities";

    private SharedPreferences mSharedPreferences;
    private Set<String> mCities;

    private List<CitiesObserver> mObservers;

    public CitiesData(@NonNull Context context) {
        mObservers = new ArrayList<>();

        mSharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        mCities = mSharedPreferences.getStringSet(Constants.SAVED_SET_OF_CITIES,
                initialSet(context));
    }

    @Override
    public boolean addCity(String cityName) {
        mCities.add(cityName);

        if (mSharedPreferences == null) {
            return false;
        } else {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.remove(Constants.SAVED_SET_OF_CITIES).apply();
            editor.putStringSet(Constants.SAVED_SET_OF_CITIES, mCities);
            editor.apply();
        }

        notifyCitiesObservers();
        return true;
    }

    @Override
    public Set<String> getCities() {
        return mCities;
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

    private Set<String> initialSet(Context context) {
        Set<String> set = new HashSet<>();
        set.add(context.getResources().getString(R.string.weather_through_sensors));
        return set;
    }
}
