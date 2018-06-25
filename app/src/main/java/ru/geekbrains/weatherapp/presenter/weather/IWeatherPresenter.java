package ru.geekbrains.weatherapp.presenter.weather;

import android.content.Context;
import android.content.Intent;

import ru.geekbrains.weatherapp.presenter.IPresenter;

public interface IWeatherPresenter extends IPresenter {

    void viewIsReady(Context context);

    void weatherDataUpdated(Intent intent);

    void weatherDataFinished(Intent intent);

    void viewIsDestroyed();
}
