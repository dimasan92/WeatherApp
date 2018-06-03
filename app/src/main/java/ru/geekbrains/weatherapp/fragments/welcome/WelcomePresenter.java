package ru.geekbrains.weatherapp.fragments.welcome;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.fragments.weather.WeatherFragment;

public class WelcomePresenter extends Fragment {

    private WelcomeFragment mFragment;

    public static WelcomePresenter newInstance() {
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

    public void onTransitionClick() {
        if (mFragment.getCityName().trim().equals("")) {
            mFragment.makeToast(R.string.empty_city_name);
            return;
        }

        WeatherFragment fragment = WeatherFragment.newInstance(createBundle());

        if(getActivity() == null){
            return;
        }
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_fragment, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void onChooseCityClick(){

    }

    private Bundle createBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.CITY_NAME, mFragment.getCityName());
        bundle.putBoolean(Constants.PARAM_PRESSURE, mFragment.getPressureParam());
        bundle.putBoolean(Constants.PARAM_WIND, mFragment.getWindParam());
        bundle.putBoolean(Constants.PARAM_HUMIDITY, mFragment.getHumidityParam());
        return bundle;
    }
}
