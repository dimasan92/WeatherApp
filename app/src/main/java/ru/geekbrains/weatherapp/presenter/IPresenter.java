package ru.geekbrains.weatherapp.presenter;

import android.support.v4.app.FragmentActivity;

import ru.geekbrains.weatherapp.view.IView;

public interface IPresenter {

    void attachView(IView view);

    void detachView();

    void assignModel(FragmentActivity activity);
}

