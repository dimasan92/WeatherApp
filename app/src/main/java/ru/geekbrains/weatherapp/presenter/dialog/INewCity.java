package ru.geekbrains.weatherapp.presenter.dialog;

import android.content.Intent;

public interface INewCity {

    void onAddNewCityClick();

    void weatherDataFinished(Intent intent);

    void viewIsDestroyed();
}

