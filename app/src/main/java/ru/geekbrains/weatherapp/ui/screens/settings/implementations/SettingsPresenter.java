package ru.geekbrains.weatherapp.ui.screens.settings.implementations;


import android.content.Context;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.ui.screens.base.BasePresenter;
import ru.geekbrains.weatherapp.ui.screens.settings.contracts.ISettingsModel;
import ru.geekbrains.weatherapp.ui.screens.settings.contracts.ISettingsPresenter;
import ru.geekbrains.weatherapp.ui.screens.settings.contracts.ISettingsView;
import ru.geekbrains.weatherapp.utils.ConverterUtils;

public class SettingsPresenter<V extends ISettingsView, M extends ISettingsModel>
        extends BasePresenter<V, M> implements ISettingsPresenter<V> {

    private CompositeDisposable mDisposables;

    public SettingsPresenter(M model, CompositeDisposable disposables) {
        super(model);
        mDisposables = disposables;
    }


    @Override
    public void viewIsReady() {
        setSavedState();
    }

    @Override
    public void sensorsSwitchChecked() {
        boolean state = mView.getShowSensorsState();
        mView.setShowSensorsState(state);
        if (state) {
            mDisposables.add(showTempReadings());
            mDisposables.add(showPressReadings());
            mDisposables.add(showHumReadings());
        } else {
            dispose();
        }
    }

    @Override
    public void detachView() {
        saveCurrentState();
        super.detachView();
    }

    @Override
    public void dispose(){
        mModel.disposeSensListeners();
        mDisposables.clear();
    }

    private void setSavedState() {
        Context context = mView.getAppContext();
        if (context == null) {
            return;
        }

        mView.setWindState(mModel.getWindState(context));
        mView.setPressureState(mModel.getPressState(context));
        mView.setHumState(mModel.getHumState(context));
        mView.setTempUnits(mModel.getTempUnits(context));
        mView.setShowSensorsState(mModel.getShowSensorsState(context));
    }

    private void saveCurrentState() {
        Context context = mView.getAppContext();
        if (context == null) {
            return;
        }

        mModel.saveWindState(context, mView.getWindState());
        mModel.savePressState(context, mView.getPressureState());
        mModel.saveHumState(context, mView.getHumState());
        mModel.saveTempUnits(context, mView.getTempUnits());
        mModel.saveShowSensorsState(context, mView.getShowSensorsState());
    }

    private Disposable showTempReadings() {
        return mModel.getTempReadings()
                .map(value -> {
                    if (mView.getTempUnits()) {
                        return mView.getAppContext()
                                .getString(R.string.sensor_temp_f_string,
                                        ConverterUtils.convertCelsiusToFahrenheit(value));
                    }
                    return mView.getAppContext()
                            .getString(R.string.sensor_temp_c_string, value);
                })
                .subscribe(value -> mView.setSensTempValue(value),
                        e -> mView.setSensTempValue(R.string.no_sensor));
    }

    private Disposable showPressReadings() {
        return mModel.getPressReadings()
                .map(value -> mView.getAppContext().getString(R.string.sensor_press_string, value))
                .subscribe(value -> mView.setSensPressValue(value),
                        e -> mView.setSensPressValue(R.string.no_sensor));
    }

    private Disposable showHumReadings() {
        return mModel.getHumReadings()
                .map(value -> mView.getAppContext().getString(R.string.sensor_hum_string, value))
                .subscribe(value -> mView.setSensHumValue(value),
                        e -> mView.setSensHumValue(R.string.no_sensor));
    }
}
