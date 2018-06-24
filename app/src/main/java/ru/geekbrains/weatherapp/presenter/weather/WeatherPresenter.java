package ru.geekbrains.weatherapp.presenter.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.Locale;

import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.presenter.Presenter;
import ru.geekbrains.weatherapp.view.IView;
import ru.geekbrains.weatherapp.view.screens.weather.IWeatherView;

public class WeatherPresenter extends Presenter implements IWeatherPresenter {

    private IWeatherView mView;

    public static WeatherPresenter newInstance(Bundle bundle) {
        WeatherPresenter presenter = new WeatherPresenter();
        presenter.setArguments(bundle);
        return presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void attachView(IView view) {
        mView = (IWeatherView) view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void viewIsReady() {
        setSettings();
        setDate();
    }

    private void setSettings() {
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        mView.setCity(bundle.getString(Constants.CITY_NAME, ""));
        mView.setVisibilityWindParam(bundle
                .getBoolean(Constants.PARAM_WIND, true));
        mView.setVisibilityPressureParam(bundle
                .getBoolean(Constants.PARAM_PRESSURE, true));
        mView.setVisibilityHumidityParam(bundle
                .getBoolean(Constants.PARAM_HUMIDITY, true));
    }

    private void setDate() {
        Locale locale = getResources().getConfiguration().locale;
        mView.setDate(String.format("%s %s", mModel.time().getDayOfMonth(locale),
                mModel.time().getMonth(locale)));

        mView.setDayOfWeek(mModel.time().getDayOfWeek(locale));
    }
}
