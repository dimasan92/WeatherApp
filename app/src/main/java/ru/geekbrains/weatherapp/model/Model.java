package ru.geekbrains.weatherapp.model;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import ru.geekbrains.weatherapp.model.citiesmodel.ICitiesData;
import ru.geekbrains.weatherapp.model.sensorsmodel.ISensorsData;
import ru.geekbrains.weatherapp.model.timemodel.ITimeData;

public class Model extends Fragment implements IModel {

    private ICitiesData mCities;
    private ISensorsData mSensors;
    private ITimeData mTime;

    public static Model newInstance() {
        return new Model();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        if (getActivity() == null) {
            return;
        }

        mCities = ModelFactory.getCities(getActivity().getApplicationContext());
        mSensors = ModelFactory.getSensors(getActivity().getApplicationContext());
        mTime = ModelFactory.getTime();
    }

    @Override
    public ICitiesData cities() {
        return mCities;
    }

    @Override
    public ISensorsData sensors() {
        return mSensors;
    }

    @Override
    public ITimeData time() {
        return mTime;
    }
}
