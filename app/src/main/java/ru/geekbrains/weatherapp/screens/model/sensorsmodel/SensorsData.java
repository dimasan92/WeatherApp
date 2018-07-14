package ru.geekbrains.weatherapp.screens.model.sensorsmodel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import ru.geekbrains.weatherapp.R;

public class SensorsData implements ISensorsData, SensorsSubject {

    private List<SensorsObserver> mObservers;
    private SensorManager mSensorManager;
    private Sensor mSensorTemperature;
    private Sensor mSensorPressure;
    private Sensor mSensorHumidity;

    private String mDegreeMark;
    private String mGPaMark;
    private String mTemperatureInd;
    private String mPressureInd;
    private String mHumidityInd;

    public SensorsData(@NonNull Context context) {
        String noSensor = context.getResources().getString(R.string.no_sensor);
        mPressureInd = noSensor;
        mTemperatureInd = noSensor;
        mHumidityInd = noSensor;

        mDegreeMark = context.getResources().getString(R.string.celsius_degree);
        mGPaMark = context.getResources().getString(R.string.gPa);

        mObservers = new ArrayList<>();

        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        if (mSensorManager == null) {
            return;
        }
        mSensorTemperature = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        mSensorPressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        mSensorHumidity = mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
    }

    @Override
    public String getTemperatureInd() {
        return mTemperatureInd;
    }

    @Override
    public String getPressureInd() {
        return mPressureInd;
    }

    @Override
    public String getHumidityInd() {
        return mHumidityInd;
    }

    @Override
    public void registerSensorsObserver(SensorsObserver o) {
        mObservers.add(o);
    }

    @Override
    public void removeSensorsObserver(SensorsObserver o) {
        mObservers.remove(o);
    }

    @Override
    public void notifySensorsObservers() {
        for (SensorsObserver o : mObservers) {
            o.updateSensors();
        }
    }

    @Override
    public void sensorsActivate() {
        if (mSensorTemperature != null) {
            mSensorManager.registerListener(listenerTemperature, mSensorTemperature,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (mSensorPressure != null) {
            mSensorManager.registerListener(listenerPressure, mSensorPressure,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (mSensorHumidity != null) {
            mSensorManager.registerListener(listenerHumidity, mSensorHumidity,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void sensorsDeactivate() {
        if (mSensorTemperature != null) {
            mSensorManager.unregisterListener(listenerTemperature, mSensorTemperature);
        }

        if (mSensorPressure != null) {
            mSensorManager.unregisterListener(listenerPressure, mSensorPressure);
        }

        if (mSensorHumidity != null) {
            mSensorManager.unregisterListener(listenerHumidity, mSensorHumidity);
        }
    }

    private SensorEventListener listenerTemperature = new SensorEventListener() {
        @SuppressLint("DefaultLocale")
        @Override
        public void onSensorChanged(SensorEvent event) {
            mTemperatureInd = String.format("%.1f %s", event.values[0], mDegreeMark);
            notifySensorsObservers();
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private SensorEventListener listenerPressure = new SensorEventListener() {
        @SuppressLint("DefaultLocale")
        @Override
        public void onSensorChanged(SensorEvent event) {
            mPressureInd = String.format("%.1f %s", event.values[0], mGPaMark);
            notifySensorsObservers();
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private SensorEventListener listenerHumidity = new SensorEventListener() {
        @SuppressLint("DefaultLocale")
        @Override
        public void onSensorChanged(SensorEvent event) {
            mHumidityInd = String.format("%.1f %s", event.values[0], "%");
            notifySensorsObservers();
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
