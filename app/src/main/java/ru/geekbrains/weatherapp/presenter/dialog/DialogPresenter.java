package ru.geekbrains.weatherapp.presenter.dialog;

import android.app.Activity;
import android.content.Intent;

import java.util.regex.Pattern;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.presenter.Presenter;
import ru.geekbrains.weatherapp.view.IView;
import ru.geekbrains.weatherapp.view.dialogs.newcity.INewCityDialog;
import ru.geekbrains.weatherapp.view.dialogs.sensorsindications.SensorsIndicationsDialog;
import ru.geekbrains.weatherapp.view.dialogs.settings.ISettingsDialog;

public class DialogPresenter extends Presenter implements IDialogPresenter {

    private IView mDialog;

    private INewCity mNewCity;
    private ISensorsIndications mSensorsIndications;
    private ISettings mSettings;

    public static DialogPresenter newInstance() {
        return new DialogPresenter();
    }

    @Override
    public void attachView(IView view) {
        mDialog = view;
    }

    @Override
    public void detachView() {
        mDialog = null;
    }

    @Override
    public INewCity newCity() {
        if (mNewCity == null) {
            mNewCity = new NewCity();
        }
        return mNewCity;
    }

    @Override
    public ISensorsIndications sensorsIndications() {
        if (mSensorsIndications == null) {
            mSensorsIndications = new SensorsIndications();
        }
        return mSensorsIndications;
    }

    @Override
    public ISettings settings() {
        if (mSettings == null) {
            mSettings = new Settings();
        }
        return mSettings;
    }

    public class NewCity implements INewCity {

        private Pattern checkCityName = Pattern.compile("^[А-ЯA-Z][а-яa-z]+$");

        @Override
        public void onAddNewCityClick() {
            String enteredText = ((INewCityDialog) mDialog).getEnteredText();
            if (isIncorrectCityName(enteredText)) {
                return;
            }

            if (mModel.cities().addCity(enteredText)) {
                ((INewCityDialog) mDialog).makeToast(R.string.success_add_city);
                ((INewCityDialog) mDialog).close();
            } else {
                ((INewCityDialog) mDialog).makeToast(R.string.fail_add_city);
            }
        }

        private boolean isIncorrectCityName(String enteredText) {
            if (checkCityName.matcher(enteredText).matches()) {
                ((INewCityDialog) mDialog).hideError();
                return false;
            }
            ((INewCityDialog) mDialog).showError(R.string.incorrect_city_name);
            return true;
        }
    }

    public class SensorsIndications implements ISensorsIndications {

        @Override
        public void viewIsReady() {
            updateSensors();
        }

        public void dialogIsVisible() {
            mModel.sensors().sensorsActivate();
        }

        public void dialogIsInvisible() {
            mModel.sensors().sensorsDeactivate();
        }

        private void updateSensors() {
            ((SensorsIndicationsDialog) mDialog)
                    .setTemperature(mModel.sensors().getTemperatureInd());
            ((SensorsIndicationsDialog) mDialog)
                    .setPressure(mModel.sensors().getPressureInd());
            ((SensorsIndicationsDialog) mDialog)
                    .setHumidity(mModel.sensors().getHumidityInd());
        }
    }

    public class Settings implements ISettings {

        @Override
        public void onParamsChooseClick() {
            Intent intent = new Intent();
            intent.putExtra(Constants.PARAM_PRESSURE, ((ISettingsDialog) mDialog).getPressureParam());
            intent.putExtra(Constants.PARAM_WIND, ((ISettingsDialog) mDialog).getWindParam());
            intent.putExtra(Constants.PARAM_HUMIDITY, ((ISettingsDialog) mDialog).getHumidityParam());
            ((ISettingsDialog) mDialog).sendResult(Activity.RESULT_OK, intent);
        }
    }
}
