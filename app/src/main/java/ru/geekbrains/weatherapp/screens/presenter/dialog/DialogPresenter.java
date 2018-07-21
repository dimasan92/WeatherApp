package ru.geekbrains.weatherapp.screens.presenter.dialog;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.regex.Pattern;

import io.reactivex.functions.Consumer;
import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.model.currentweather.WeatherResponce;
import ru.geekbrains.weatherapp.model.settingsmodel.SettingsData;
import ru.geekbrains.weatherapp.screens.base.IView;
import ru.geekbrains.weatherapp.screens.model.sensorsmodel.SensorsObserver;
import ru.geekbrains.weatherapp.screens.model.weathermodel.WeatherListener;
import ru.geekbrains.weatherapp.screens.view.dialogs.newcity.INewCityDialog;
import ru.geekbrains.weatherapp.screens.view.dialogs.sensorsindications.SensorsIndicationsDialog;
import ru.geekbrains.weatherapp.screens.view.dialogs.settings.ISettingsDialog;

public class DialogPresenter extends Fragment implements IDialogPresenter {

    private static final String TAG = DialogPresenter.class.getSimpleName();

    private IView mDialog;

    private INewCity mNewCity;
    private ISensorsIndications mSensorsIndications;
    private ISettings mSettings;

    public static DialogPresenter newInstance() {
        return new DialogPresenter();
    }

//    @Override
//    public void attachView(IView view) {
//        mDialog = view;
//    }
//
//    @Override
//    public void detachView() {
//        mDialog = null;
//    }

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

    @Override
    public void attachView(IView view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void getDataForToolBar(Consumer toolbarSetter) {

    }

    public class NewCity implements INewCity {

        private Pattern checkCityName = Pattern.compile("^[А-ЯA-Z][а-яa-zА-ЯA-Z\\-]+$");

        @Override
        public void onAddNewCityClick() {
            String enteredText = ((INewCityDialog) mDialog).getEnteredText();
            if (isIncorrectCityName(enteredText)) {
                return;
            }
//            mModel.weather().setWeatherListener(weatherListener());
//            mModel.weather().request(enteredText);
        }

        private boolean isIncorrectCityName(String enteredText) {
            if (checkCityName.matcher(enteredText).matches()) {
                ((INewCityDialog) mDialog).hideError();
                return false;
            }
            ((INewCityDialog) mDialog).showError(R.string.incorrect_city_name);
            return true;
        }

        private WeatherListener weatherListener() {
            return new WeatherListener() {
                @Override
                public void onSuccess(WeatherResponce request) {
//                    if (mModel.cities().addCity(request.getName())) {
//                        ((INewCityDialog) mDialog).makeToast(R.string.success_add_city);
//                        ((INewCityDialog) mDialog).close();
//                    } else {
//                        ((INewCityDialog) mDialog).makeToast(R.string.fail_add_city);
//                    }
                }

                @Override
                public void onFailure(String error) {
                    if (error.equals(String.valueOf(Constants.ERROR_DATA_CODE))) {
                        ((INewCityDialog) mDialog).showError(R.string.city_is_not_exists);
                    } else {
                        ((INewCityDialog) mDialog).makeToast(R.string.fail_add_city);
                        Log.e(TAG, error);
                    }
                }
            };
        }
    }

    public class SensorsIndications implements ISensorsIndications, SensorsObserver {

        @Override
        public void viewIsReady() {
            updateSensors();
//            mModel.sensorsSubject().registerSensorsObserver(this);
        }

        @Override
        public void dialogIsVisible() {
//            mModel.sensors().sensorsActivate();
        }

        @Override
        public void dialogIsInvisible() {
//            mModel.sensors().sensorsDeactivate();
        }

        @Override
        public void updateSensors() {
//            ((SensorsIndicationsDialog) mDialog)
//                    .setTemperature(mModel.sensors().getTemperatureInd());
//            ((SensorsIndicationsDialog) mDialog)
//                    .setPressure(mModel.sensors().getPressureInd());
//            ((SensorsIndicationsDialog) mDialog)
//                    .setHumidity(mModel.sensors().getHumidityInd());
        }
    }

    public class Settings implements ISettings {

        @Override
        public void viewIsReady(Context context) {
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
