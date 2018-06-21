package ru.geekbrains.weatherapp.model.sensormodel;

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
import ru.geekbrains.weatherapp.model.datamodel.DataObserver;
import ru.geekbrains.weatherapp.model.datamodel.DataSubject;

public class SensorModel extends Fragment implements SensorSubject {

    private List<SensorObserver> mObservers;

    public static SensorModel newInstance() {
        return new SensorModel();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        mObservers = new ArrayList<>();
    }

    @Override
    public void registerSensorObserver(SensorObserver o) {
        mObservers.add(o);
    }

    @Override
    public void removeSensorObserver(SensorObserver o) {
        mObservers.remove(o);
    }

    @Override
    public void notifySensorObservers() {
        for (SensorObserver o : mObservers) {
            o.updateSensor();
        }
    }
}
