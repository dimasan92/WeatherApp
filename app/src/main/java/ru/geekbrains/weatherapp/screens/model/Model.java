package ru.geekbrains.weatherapp.screens.model;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.Objects;

import ru.geekbrains.weatherapp.screens.model.citiesmodel.CitiesSubject;
import ru.geekbrains.weatherapp.screens.model.citiesmodel.ICitiesData;
import ru.geekbrains.weatherapp.screens.model.sensorsmodel.ISensorsData;
import ru.geekbrains.weatherapp.screens.model.sensorsmodel.SensorsSubject;
import ru.geekbrains.weatherapp.screens.model.weathermodel.IWeatherData;

public class Model extends Fragment implements IModel {

    private ICitiesData mCities;
    private ISensorsData mSensors;
    private IWeatherData mWeather;

    public static Model newInstance() {
        return new Model();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public ICitiesData cities() {
        if (mCities == null) {
            mCities = ModelFactory
                    .getCities(Objects.requireNonNull(getActivity()).getApplicationContext());
        }
        return mCities;
    }

    @Override
    public ISensorsData sensors() {
        if (mSensors == null) {
            mSensors = ModelFactory
                    .getSensors(Objects.requireNonNull(getActivity()).getApplicationContext());
        }
        return mSensors;
    }

    @Override
    public IWeatherData weather() {
        if (mWeather == null) {
            mWeather = ModelFactory.getWeather();
        }
        return mWeather;
    }

    @Override
    public CitiesSubject citiesSubject() {
        return (CitiesSubject) cities();
    }

    @Override
    public SensorsSubject sensorsSubject() {
        return (SensorsSubject) sensors();
    }
}
