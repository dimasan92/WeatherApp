package ru.geekbrains.weatherapp.fragments.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.model.datamodel.DataModel;

public abstract class AbstractPresenter extends Fragment{

    protected Fragment mScreen;
    protected DataModel mModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void attachView(Fragment view) {
        mScreen = view;
    }

    public void detachView() {
        mScreen = null;
    }

    public void viewIsReady(){

    }

    public void assignModel(FragmentActivity activity) {
        FragmentManager fm = activity.getSupportFragmentManager();
        mModel = (DataModel) fm.findFragmentByTag(Constants.DATA_MODEL_TAG);

        if (mModel == null) {
            mModel = DataModel.newInstance();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(mModel, Constants.DATA_MODEL_TAG);
            ft.commit();
        }
    }
}
