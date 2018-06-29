package ru.geekbrains.weatherapp.presenter.weather;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.Locale;

import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.model.weathermodel.WeatherService;
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
    public void viewIsReady(Context context) {
        setSettings();
        setDate(context);

        mView.setWind("Загрузка данных");

        mView.startWeatherService();

        mView.registerReceivers();
    }

    @Override
    public void weatherDataUpdated(Intent intent) {
        String result = intent
                .getStringExtra(Constants.EXTRA_WEATHER_SERVICE_FINISH);
        mView.setWind(result);
    }

    @Override
    public void weatherDataFinished(Intent intent) {
        String result = intent
                .getStringExtra(Constants.EXTRA_WEATHER_SERVICE_UPDATE);
        mView.setWind(result);
    }

    @Override
    public void viewIsDestroyed() {
        mView.unregisterReceivers();
    }

    private void setSettings() {
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        mView.setCity(bundle.getString(Constants.CITY_NAME, ""));
        mView.setVisibilityWindParam(mModel.settings().getParamWind());
        mView.setVisibilityPressureParam(mModel.settings().getParamPressure());
        mView.setVisibilityHumidityParam(mModel.settings().getParamHumidity());
    }

    private void setDate(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        mView.setDate(String.format("%s %s", mModel.time().getDayOfMonth(locale),
                mModel.time().getMonth(locale)));

        mView.setDayOfWeek(mModel.time().getDayOfWeek(locale));
    }
}
