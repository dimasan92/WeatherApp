package ru.geekbrains.weatherapp.fragments.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.common.listener.Observer;
import ru.geekbrains.weatherapp.model.DataModel;
import ru.geekbrains.weatherapp.model.SimpleDataModel;

public abstract class AbstractPresenter extends Fragment implements Presenter, Observer {

    protected Fragment mView;
    protected DataModel mModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void attachView(Fragment view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void assignModel(FragmentActivity activity) {
        FragmentManager fm = activity.getSupportFragmentManager();
        mModel = (SimpleDataModel) fm.findFragmentByTag(Constants.DATA_MODEL_TAG);

        if (mModel == null) {
            mModel = SimpleDataModel.newInstance();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(((SimpleDataModel)mModel), Constants.DATA_MODEL_TAG);
            ft.commit();
        }
    }
}
