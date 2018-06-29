package ru.geekbrains.weatherapp.model;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.Objects;

import ru.geekbrains.weatherapp.model.citiesmodel.CitiesSubject;
import ru.geekbrains.weatherapp.model.citiesmodel.ICitiesData;
import ru.geekbrains.weatherapp.model.sensorsmodel.ISensorsData;
import ru.geekbrains.weatherapp.model.sensorsmodel.SensorsSubject;
import ru.geekbrains.weatherapp.model.settingsmodel.ISettingsData;
import ru.geekbrains.weatherapp.model.timemodel.ITimeData;

public class Model extends Fragment implements IModel {

    private ICitiesData mCities;
    private ISensorsData mSensors;
    private ITimeData mTime;
    private ISettingsData mSettings;

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
    public ITimeData time() {
        if (mTime == null) {
            mTime = ModelFactory.getTime();
        }
        return mTime;
    }

    @Override
    public ISettingsData settings() {
        if (mSettings == null) {
            mSettings = ModelFactory
                    .getSettings(Objects.requireNonNull(getActivity()).getApplicationContext());
        }
        return mSettings;
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
