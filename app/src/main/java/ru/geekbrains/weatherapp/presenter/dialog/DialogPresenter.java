package ru.geekbrains.weatherapp.presenter.dialog;

import android.content.Context;
import android.content.Intent;

import java.util.regex.Pattern;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.model.sensorsmodel.SensorsObserver;
import ru.geekbrains.weatherapp.model.settingsmodel.SettingsData;
import ru.geekbrains.weatherapp.presenter.Presenter;
import ru.geekbrains.weatherapp.utils.ParseWeatherUtils;
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

        private Pattern checkCityName = Pattern.compile("^[А-ЯA-Z][а-яa-zА-ЯA-Z\\-]+$");

        @Override
        public void onAddNewCityClick() {
            String enteredText = ((INewCityDialog) mDialog).getEnteredText();
            if (isIncorrectCityName(enteredText)) {
                return;
            }

            ((INewCityDialog) mDialog).startWeatherService(enteredText);
            ((INewCityDialog) mDialog).registerReceiver();
        }

        @Override
        public void weatherDataFinished(Intent intent) {
            String result = intent
                    .getStringExtra(Constants.EXTRA_WEATHER_SERVICE_FINISH);
            int cod = ParseWeatherUtils.getCod(result);
            if (cod != ParseWeatherUtils.ERROR_CODE) {
                if (mModel.cities().addCity(((INewCityDialog) mDialog).getEnteredText())) {
                    ((INewCityDialog) mDialog).makeToast(R.string.success_add_city);
                    ((INewCityDialog) mDialog).close();
                } else {
                    ((INewCityDialog) mDialog).makeToast(R.string.fail_add_city);
                }
            } else {
                ((INewCityDialog) mDialog).showError(R.string.city_is_not_exists);
            }
        }

        @Override
        public void viewIsDestroyed() {
            ((INewCityDialog) mDialog).unregisterReceiver();
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

    public class SensorsIndications implements ISensorsIndications, SensorsObserver {

        @Override
        public void viewIsReady() {
            updateSensors();
            mModel.sensorsSubject().registerSensorsObserver(this);
        }

        @Override
        public void dialogIsVisible() {
            mModel.sensors().sensorsActivate();
        }

        @Override
        public void dialogIsInvisible() {
            mModel.sensors().sensorsDeactivate();
        }

        @Override
        public void updateSensors() {
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
        public void viewIsReady() {
            if (getActivity() == null) {
                return;
            }
            Context context = getActivity().getApplicationContext();
            ((ISettingsDialog) mDialog).setWindParam(SettingsData.getParamWind(context));
            ((ISettingsDialog) mDialog).setPressureParam(SettingsData.getParamPressure(context));
            ((ISettingsDialog) mDialog).setHumidityParam(SettingsData.getParamHumidity(context));
        }

        @Override
        public void onParamsChooseClick() {
            if (getActivity() == null) {
                return;
            }
            Context context = getActivity().getApplicationContext();
            SettingsData.saveIndState(context,
                    ((ISettingsDialog) mDialog).getWindParam(),
                    ((ISettingsDialog) mDialog).getPressureParam(),
                    ((ISettingsDialog) mDialog).getHumidityParam()
            );
        }
    }
}
