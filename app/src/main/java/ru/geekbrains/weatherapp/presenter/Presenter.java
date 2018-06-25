package ru.geekbrains.weatherapp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import ru.geekbrains.weatherapp.common.FragmentFactory;
import ru.geekbrains.weatherapp.model.IModel;

public abstract class Presenter extends Fragment implements IPresenter {

    protected IModel mModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void assignModel(FragmentActivity activity) {
        mModel = FragmentFactory.getModel(activity);
    }
}
