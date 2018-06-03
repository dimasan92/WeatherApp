package ru.geekbrains.weatherapp.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public class WeatherPresenter extends Fragment {
    private WeatherFragment mFragment;

    public static WeatherPresenter newInstance() {
        return new WeatherPresenter();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void attachView(WeatherFragment fragment) {
        mFragment = fragment;
    }

    public void detachView() {
        mFragment = null;
    }
}
