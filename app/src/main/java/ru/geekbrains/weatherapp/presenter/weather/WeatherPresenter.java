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
    private FinishReceiver mFinishReceiver;
    private UpdateReceiver mUpdateReceiver;

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

        Intent intent = new Intent(context, WeatherService.class);
        context.startService(intent);

        registerReceivers(context);
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

    private void setDate(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        mView.setDate(String.format("%s %s", mModel.time().getDayOfMonth(locale),
                mModel.time().getMonth(locale)));

        mView.setDayOfWeek(mModel.time().getDayOfWeek(locale));
    }

    private void registerReceivers(Context context){
        mFinishReceiver = new FinishReceiver();
        mUpdateReceiver = new UpdateReceiver();

        IntentFilter intentFilter = new IntentFilter(
                Constants.ACTION_WEATHER_SERVICE_FINISH);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        context.registerReceiver(mFinishReceiver, intentFilter);

        // Регистрируем второй приёмник
        IntentFilter updateIntentFilter = new IntentFilter(
                Constants.ACTION_WEATHER_SERVICE_UPDATE);
        updateIntentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        context.registerReceiver(mUpdateReceiver, updateIntentFilter);
    }

    public class FinishReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent
                    .getStringExtra(Constants.EXTRA_WEATHER_SERVICE_FINISH);
            mView.setWind(result);
        }
    }

    public class UpdateReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent
                    .getStringExtra(Constants.EXTRA_WEATHER_SERVICE_UPDATE);
            mView.setWind(result);
        }
    }
}
