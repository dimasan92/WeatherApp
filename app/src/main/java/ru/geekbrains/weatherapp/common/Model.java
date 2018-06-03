package ru.geekbrains.weatherapp.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.HashSet;
import java.util.Set;

public class Model extends Fragment {

    private SharedPreferences mSharedPreferences;

    private Set<String> cities;

    public static Model newInstance() {
        return new Model();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        if(getActivity() == null){
            return;
        }
        mSharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        cities = mSharedPreferences.getStringSet(Constants.SAVED_SET_OF_CITIES, new HashSet<>());
    }

    public boolean addCity(String cityName){

        if(getActivity() == null){
            return false;
        }
        cities.add(cityName);
        mSharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.clear().apply();
        editor.putStringSet(Constants.SAVED_SET_OF_CITIES, cities);
        editor.apply();
        return true;
    }

    public Set<String> getCities() {
        return cities;
    }
}
