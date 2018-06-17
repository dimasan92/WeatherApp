package ru.geekbrains.weatherapp.model;

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

import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.common.listener.Observer;
import ru.geekbrains.weatherapp.common.listener.Subject;
import ru.geekbrains.weatherapp.fragments.welcome.WelcomePresenter;

public class DataModel extends Fragment implements Subject{

    private SharedPreferences mSharedPreferences;
    private Set<String> mData;

    private List<Observer> mObservers;

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
        mData = mSharedPreferences.getStringSet(Constants.SAVED_SET_OF_CITIES, new HashSet<>());
    }

    @Override
    public void registerObserver(Observer o) {
        mObservers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        mObservers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : mObservers) {
            o.update();
        }
    }

    public boolean addCity(String cityName) {
        mData.add(cityName);
        mSharedPreferences = Objects.requireNonNull(getActivity())
                .getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.remove(Constants.SAVED_SET_OF_CITIES).apply();
        editor.putStringSet(Constants.SAVED_SET_OF_CITIES, mData);
        editor.apply();
        notifyObservers();
        return true;
    }

    public Set<String> getData() {
        return mData;
    }
}
