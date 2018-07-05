package ru.geekbrains.weatherapp.presenter.weather;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.model.settingsmodel.SettingsData;
import ru.geekbrains.weatherapp.model.timemodel.TimeData;
import ru.geekbrains.weatherapp.model.weathermodel.WeatherListener;
import ru.geekbrains.weatherapp.model.weathermodel.data.WeatherRequest;
import ru.geekbrains.weatherapp.presenter.Presenter;
import ru.geekbrains.weatherapp.view.IView;
import ru.geekbrains.weatherapp.view.screens.weather.IWeatherView;

public class WeatherPresenter extends Presenter implements IWeatherPresenter {

    private static final String TAG = WeatherPresenter.class.getSimpleName();

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
        setSettings(context);
        setDate(context);
        setParams();
    }

    private void setSettings(Context context) {
        mView.setVisibilityWindParam(SettingsData.getParamWind(context));
        mView.setVisibilityPressureParam(SettingsData.getParamPressure(context));
        mView.setVisibilityHumidityParam(SettingsData.getParamHumidity(context));
    }

    private void setDate(Context context) {
        mView.setDate(String.format("%s %s", TimeData.getDayOfMonth(context),
                TimeData.getMonth(context)));

        mView.setDayOfWeek(TimeData.getDayOfWeek(context));
    }

    private void setParams() {
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        String cityName = bundle.getString(Constants.CITY_NAME, "");
        mModel.weather().setWeatherListener(weatherListener());
        mModel.weather().request(cityName);
    }

    private WeatherListener weatherListener() {
        return new WeatherListener() {
            @Override
            public void onSuccess(WeatherRequest request) {
                mView.setCity(request.getName());
                mView.setCurrentTemperature(String.format("%.1f %s", request.getMain().getTemp(),
                        getString(R.string.celsius_degree)));
                mView.setDescription(request.getWeather()[0].getDescription());
                mView.setWind(getWind(request.getWind().getSpeed()));
                mView.setPressure(String.format("%d %s", request.getMain().getPressure(),
                        getString(R.string.gPa)));
                mView.setHumidity(String.format("%d %s", request.getMain().getHumidity(),"%"));
            }

            @Override
            public void onFailure(String error) {
                Log.e(TAG, error);
            }
        };
    }

    private String getWind(float speed) {
        String wind = getString(R.string.wind_calm);
        if (speed > 0.5 && speed <= 1.8) {
            wind = getString(R.string.wind_light_air);
        } else if (speed > 1.8 && speed <= 3.3) {
            wind = getString(R.string.wind_light_air);
        } else if (speed > 3.3 && speed <= 5.2) {
            wind = getString(R.string.wind_light_breeze);
        } else if (speed > 5.2 && speed <= 7.4) {
            wind = getString(R.string.wind_gentle_breeze);
        } else if (speed > 7.4 && speed <= 9.8) {
            wind = getString(R.string.wind_moderate_breeze);
        } else if (speed > 9.8 && speed <= 12.4) {
            wind = getString(R.string.wind_fresh_breeze);
        } else if (speed > 12.5 && speed <= 15.2) {
            wind = getString(R.string.wind_strong_breeze);
        } else if (speed > 15.2 && speed <= 18.2) {
            wind = getString(R.string.wind_moderate_gale);
        } else if (speed > 18.2 && speed <= 21.5) {
            wind = getString(R.string.wind_fresh_gale);
        } else if (speed > 21.5 && speed <= 25.1) {
            wind = getString(R.string.wind_strong_gale);
        } else if (speed > 25.1 && speed <= 29.0) {
            wind = getString(R.string.wind_whole_gale);
        } else {
            wind = getString(R.string.wind_storm);
        }
        return wind;
    }
}
