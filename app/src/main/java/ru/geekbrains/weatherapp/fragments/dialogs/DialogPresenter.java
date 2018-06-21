package ru.geekbrains.weatherapp.fragments.dialogs;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.regex.Pattern;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.fragments.base.AbstractDialog;
import ru.geekbrains.weatherapp.fragments.base.AbstractPresenter;
import ru.geekbrains.weatherapp.model.sensormodel.SensorModel;
import ru.geekbrains.weatherapp.model.sensormodel.SensorObserver;

public class DialogPresenter extends AbstractPresenter implements SensorObserver{

    private Pattern checkCityName = Pattern.compile("^[А-ЯA-Z][а-яa-z]+$");
    private SensorModel mSensorModel;


    public static DialogPresenter newInstance() {
        return new DialogPresenter();
    }

    @Override
    public void updateSensor() {
        ((SensorDialog)mScreen).setTemp(mSensorModel.getTempInd());
        ((SensorDialog)mScreen).setPress(mSensorModel.getPressInd());
        ((SensorDialog)mScreen).setHumm(mSensorModel.getHummInd());
    }

    @Override
    public void viewIsReady() {
        if(mScreen instanceof SensorDialog){
            updateSensor();
        }
    }

    public void onConfirmAddClick() {

        if (incorrectCityName()) {
            return;
        }

        if (mModel.addCity(((AddCityDialog) mScreen).getEnteredText())) {
            ((AbstractDialog) mScreen).makeToast(R.string.success_add_city);
            ((AddCityDialog) mScreen).dismiss();
        } else {
            ((AbstractDialog) mScreen).makeToast(R.string.fail_add_city);
        }
    }

    public void onParamsChooseClick() {
        Intent intent = new Intent();
        intent.putExtra(Constants.PARAM_PRESSURE, ((SettingsDialog) mScreen).getPressureParam());
        intent.putExtra(Constants.PARAM_WIND, ((SettingsDialog) mScreen).getWindParam());
        intent.putExtra(Constants.PARAM_HUMIDITY, ((SettingsDialog) mScreen).getHumidityParam());
        ((SettingsDialog) mScreen).sendResult(Activity.RESULT_OK, intent);
    }

    public void assignModel(FragmentActivity activity) {
        FragmentManager fm = activity.getSupportFragmentManager();
        mSensorModel = (SensorModel) fm.findFragmentByTag(Constants.SENSOR_MODEL_TAG);

        if (mSensorModel == null) {
            mSensorModel = SensorModel.newInstance();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(mSensorModel, Constants.SENSOR_MODEL_TAG);
            ft.commit();
        }
        mSensorModel.registerSensorObserver(this);
    }

    public void sensorDialogIsVisible(){
        mSensorModel.sensorsActivate();
    }

    public void sensorDialogIsUnVisible(){
        mSensorModel.sensorsDeactivate();
    }

    private boolean incorrectCityName() {
        String enteredText = ((AddCityDialog) mScreen).getEnteredText();
        if (checkCityName.matcher(enteredText).matches()) {
            ((AddCityDialog) mScreen).hideError();
            return false;
        }
        ((AddCityDialog) mScreen).showError(R.string.incorrect_city_name);
        return true;
    }
}
