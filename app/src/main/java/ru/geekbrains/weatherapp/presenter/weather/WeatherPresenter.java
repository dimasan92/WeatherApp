package ru.geekbrains.weatherapp.presenter.weather;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.model.settingsmodel.SettingsData;
import ru.geekbrains.weatherapp.model.timemodel.TimeData;
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
    }

    private void setSettings() {
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        mView.setCity(bundle.getString(Constants.CITY_NAME, ""));

        if (getActivity() == null) {
            return;
        }
        Context context = getActivity().getApplicationContext();
        mView.setVisibilityWindParam(SettingsData.getParamWind(context));
        mView.setVisibilityPressureParam(SettingsData.getParamPressure(context));
        mView.setVisibilityHumidityParam(SettingsData.getParamHumidity(context));
    }

    private void setDate(Context context) {
        mView.setDate(String.format("%s %s", TimeData.getDayOfMonth(context),
                TimeData.getMonth(context)));

        mView.setDayOfWeek(TimeData.getDayOfWeek(context));
    }
}
