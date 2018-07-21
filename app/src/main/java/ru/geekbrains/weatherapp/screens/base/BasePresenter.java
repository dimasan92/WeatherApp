package ru.geekbrains.weatherapp.screens.base;

public abstract class BasePresenter<V extends IView, M extends IModel>
        implements IPresenter<V> {

    protected V mView;

    protected M mModel;

    @Override
    public void attachView(V view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
