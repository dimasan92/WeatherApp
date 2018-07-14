package ru.geekbrains.weatherapp.screens.base;

public interface IPresenter<T extends IView> {

    void attachView(T view);

    void detachView();

//    void assignModel(FragmentActivity activity);
}

