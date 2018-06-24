package ru.geekbrains.weatherapp.presenter.weather;

import android.content.Context;

import ru.geekbrains.weatherapp.presenter.IPresenter;

public interface IWeatherPresenter extends IPresenter {

    void viewIsReady(Context context);
}
