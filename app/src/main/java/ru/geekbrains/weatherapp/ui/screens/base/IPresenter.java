package ru.geekbrains.weatherapp.ui.screens.base;

public interface IPresenter<V extends IView> {

    void attachView(V view);

    void detachView();

    void viewIsReady();

    void dispose();
}

