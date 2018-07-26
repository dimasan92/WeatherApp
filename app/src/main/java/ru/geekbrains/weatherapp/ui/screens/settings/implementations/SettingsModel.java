package ru.geekbrains.weatherapp.ui.screens.settings.implementations;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.exceptions.NoSensorException;
import ru.geekbrains.weatherapp.ui.screens.settings.contracts.ISettingsModel;
import ru.geekbrains.weatherapp.utils.PrefsUtils;

public class SettingsModel implements ISettingsModel {

    private final SensorManager mSensorManager;

    private SensorEventListener mTempListener;
    private SensorEventListener mPressListener;
    private SensorEventListener mHumListener;

    public SettingsModel(SensorManager sensorManager) {
        mSensorManager = sensorManager;
    }

    @Override
    public void saveWindState(Context context, boolean isChecked) {
        PrefsUtils.saveWindState(context, isChecked);
    }

    @Override
    public boolean getWindState(Context context) {
        return PrefsUtils.getWindState(context);
    }

    @Override
    public void savePressState(Context context, boolean isChecked) {
        PrefsUtils.savePressState(context, isChecked);
    }

    @Override
    public boolean getPressState(Context context) {
        return PrefsUtils.getPressState(context);
    }

    @Override
    public void saveHumState(Context context, boolean isChecked) {
        PrefsUtils.saveHumState(context, isChecked);
    }

    @Override
    public boolean getHumState(Context context) {
        return PrefsUtils.getHumState(context);
    }

    @Override
    public void saveTempUnits(Context context, boolean isChecked) {
        PrefsUtils.saveTempUnits(context, isChecked);
    }

    @Override
    public boolean getTempUnits(Context context) {
        return PrefsUtils.getTempUnits(context);
    }

    @Override
    public void saveShowSensorsState(Context context, boolean isChecked) {
        PrefsUtils.saveShowSensorsState(context, isChecked);
    }

    @Override
    public boolean getShowSensorsState(Context context) {
        return PrefsUtils.getShowSensorsState(context);
    }

    @Override
    public Observable<Float> getTempReadings() {
        return Observable.create(emitter -> {
            if(mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) == null){
                emitter.onError(new NoSensorException());
                return;
            }
            mTempListener = getSensorListener(emitter);
            mSensorManager.registerListener(mTempListener,
                    mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE),
                    SensorManager.SENSOR_DELAY_NORMAL);
        });
    }

    @Override
    public Observable<Float> getPressReadings() {
        return Observable.create(emitter -> {
            if(mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) == null){
                emitter.onError(new NoSensorException());
                return;
            }
            mPressListener = getSensorListener(emitter);
            mSensorManager.registerListener(mPressListener,
                    mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE),
                    SensorManager.SENSOR_DELAY_NORMAL);
        });
    }

    @Override
    public Observable<Float> getHumReadings() {
        return Observable.create(emitter -> {
            if(mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY) == null){
                emitter.onError(new NoSensorException());
                return;
            }
            mHumListener = getSensorListener(emitter);
            mSensorManager.registerListener(mHumListener,
                    mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY),
                    SensorManager.SENSOR_DELAY_NORMAL);
        });
    }

    @Override
    public void disposeSensListeners() {
        mSensorManager.unregisterListener(mTempListener);
        mSensorManager.unregisterListener(mPressListener);
        mSensorManager.unregisterListener(mHumListener);
    }

    private SensorEventListener getSensorListener(ObservableEmitter<Float> emitter) {
        return new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                emitter.onNext(event.values[0]);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }
}
