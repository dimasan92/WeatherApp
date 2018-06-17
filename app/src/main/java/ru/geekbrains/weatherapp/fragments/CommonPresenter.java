package ru.geekbrains.weatherapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.model.DataModel;

public class CommonPresenter extends Fragment {

    protected Fragment mView;
    protected DataModel mModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void attachView(Fragment view) {
        mView = view;
    }

    public void detachView() {
        mView = null;
    }

    public void viewIsReady() {

    }

    public void assignModel(FragmentActivity activity) {
        FragmentManager fm = activity.getSupportFragmentManager();
        mModel = (DataModel) fm.findFragmentByTag(Constants.MODEL_TAG);

        if (mModel == null) {
            mModel = DataModel.newInstance();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(mModel, Constants.MODEL_TAG);
            ft.commit();
        }
    }
}
