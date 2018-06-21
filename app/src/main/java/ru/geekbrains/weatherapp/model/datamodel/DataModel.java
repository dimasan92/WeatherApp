package ru.geekbrains.weatherapp.model.datamodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.common.Constants;

public class DataModel extends Fragment implements DataSubject {

    private SharedPreferences mSharedPreferences;
    private Set<String> mCities;

    private List<DataObserver> mObservers;

    public static DataModel newInstance() {
        return new DataModel();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        mObservers = new ArrayList<>();

        mSharedPreferences = Objects.requireNonNull(getActivity())
                .getPreferences(Context.MODE_PRIVATE);
        mCities = mSharedPreferences.getStringSet(Constants.SAVED_SET_OF_CITIES, initialSet());
    }

    @Override
    public void registerDataObserver(DataObserver o) {
        mObservers.add(o);
    }

    @Override
    public void removeDataObserver(DataObserver o) {
        mObservers.remove(o);
    }

    @Override
    public void notifyDataObservers() {
        for (DataObserver o : mObservers) {
            o.updateData();
        }
    }

    public boolean addCity(String cityName) {
        mCities.add(cityName);
        mSharedPreferences = Objects.requireNonNull(getActivity())
                .getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.remove(Constants.SAVED_SET_OF_CITIES).apply();
        editor.putStringSet(Constants.SAVED_SET_OF_CITIES, mCities);
        editor.apply();
        notifyDataObservers();
        return true;
    }

    public Set<String> getCities() {
        return mCities;
    }

    private Set<String> initialSet(){
        Set<String> set = new HashSet<>();
        set.add(Objects.requireNonNull(getActivity())
                .getResources().getString(R.string.weather_through_sensors));
        return set;
    }
}
