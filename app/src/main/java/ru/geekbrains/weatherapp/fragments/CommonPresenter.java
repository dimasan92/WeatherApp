package ru.geekbrains.weatherapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.common.Model;

public class CommonPresenter extends Fragment {

    protected CommonView mView;
    protected Model mModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void attachView(CommonView view) {
        mView = view;
    }

    public void detachView() {
        mView = null;
    }

    public void viewIsReady() {

    }

    public void assignModel(FragmentActivity activity) {
        FragmentManager fm = activity.getSupportFragmentManager();
        mModel = (Model) fm.findFragmentByTag(Constants.MODEL_TAG);

        if (mModel == null) {
            mModel = Model.newInstance();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(mModel, Constants.MODEL_TAG);
            ft.commit();
        }
    }
}
