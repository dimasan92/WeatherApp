package ru.geekbrains.weatherapp.screens.base;

import java.util.List;

import io.reactivex.functions.Consumer;

public interface IPresenter<V extends IView> {

    void attachView(V view);

    void detachView();

    void getDataForToolBar(Consumer<List<String>> toolbarSetter);
}

