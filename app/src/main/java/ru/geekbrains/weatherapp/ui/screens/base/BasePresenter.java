package ru.geekbrains.weatherapp.ui.screens.base;

public abstract class BasePresenter<V extends IView, M extends IModel>
        implements IPresenter<V> {

    protected V mView;

    protected M mModel;

    public BasePresenter (M model){
        mModel = model;
    }

    @Override
    public void attachView(V view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
