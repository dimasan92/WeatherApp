package ru.geekbrains.weatherapp.welcome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.weather.WeatherFragment;

public class WelcomePresenter extends Fragment {

    private WelcomeFragment mFragment;

    public static WelcomePresenter init() {
        return new WelcomePresenter();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void attachView(WelcomeFragment fragment) {
        mFragment = fragment;
    }

    public void detachView() {
        mFragment = null;
    }

    public void transitionClick() {
        String cityName = mFragment.getCityName();
        if (cityName.trim().equals("")) {
            mFragment.makeToast(R.string.empty_city_name);
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putString(Constants.CITY_NAME, cityName);
        bundle.putBoolean(Constants.PARAM_PRESSURE, mFragment.getPressureParam());
        bundle.putBoolean(Constants.PARAM_WIND, mFragment.getWindParam());
        bundle.putBoolean(Constants.PARAM_HUMIDITY, mFragment.getHumidityParam());

        WeatherFragment fragment = WeatherFragment.init(bundle);

        if (getFragmentManager() != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.main_fragment, fragment);
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}
