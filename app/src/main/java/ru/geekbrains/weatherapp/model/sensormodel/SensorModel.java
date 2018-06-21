package ru.geekbrains.weatherapp.model.sensormodel;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ru.geekbrains.weatherapp.R;

public class SensorModel extends Fragment implements SensorSubject {

    private List<SensorObserver> mObservers;
    private SensorManager mSensorManager;
    private Sensor mSensorTemp;
    private Sensor mSensorPress;
    private Sensor mSensorHumm;

    private String mDegree;
    private String mGPa;
    private String mTempInd;
    private String mPressInd;
    private String mHummInd;

    public String getTempInd() {
        return mTempInd;
    }

    public String getPressInd() {
        return mPressInd;
    }

    public String getHummInd() {
        return mHummInd;
    }

    public static SensorModel newInstance() {
        return new SensorModel();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        String noSensor = Objects.requireNonNull(getActivity())
                .getResources().getString(R.string.no_sensor);
        mPressInd = noSensor;
        mTempInd = noSensor;
        mHummInd = noSensor;

        mDegree = getActivity().getResources().getString(R.string.celsius_degree);
        mGPa = getActivity().getResources().getString(R.string.gPa);

        mObservers = new ArrayList<>();

        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        if (mSensorManager == null) {
            return;
        }
        mSensorTemp = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        mSensorPress = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        mSensorHumm = mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
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

    public void sensorsActivate() {
        if (mSensorTemp != null) {
            mSensorManager.registerListener(listenerTemp, mSensorTemp,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (mSensorPress != null) {
            mSensorManager.registerListener(listenerPress, mSensorPress,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (mSensorHumm != null) {
            mSensorManager.registerListener(listenerHumm, mSensorHumm,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    public void sensorsDeactivate() {
        if (mSensorTemp != null) {
            mSensorManager.unregisterListener(listenerTemp, mSensorTemp);
        }

        if (mSensorPress != null) {
            mSensorManager.unregisterListener(listenerPress, mSensorPress);
        }

        if (mSensorHumm != null) {
            mSensorManager.unregisterListener(listenerHumm, mSensorHumm);
        }
    }

    private SensorEventListener listenerTemp = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            mTempInd = String.format(getResources().getConfiguration().locale,
                    "%.1f %s", event.values[0], mDegree);
            notifySensorObservers();
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private SensorEventListener listenerPress = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            mPressInd = String.format(getResources().getConfiguration().locale,
                    "%.1f %s", event.values[0], mGPa);
            notifySensorObservers();
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private SensorEventListener listenerHumm = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            mHummInd = String.format(getResources().getConfiguration().locale,
                    "%.1f %s", event.values[0], "%");
            notifySensorObservers();
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
