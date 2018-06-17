package ru.geekbrains.weatherapp.fragments.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public interface Presenter {

    void attachView(Fragment view);

    void detachView();

    void viewIsReady();

    void assignModel(FragmentActivity activity);
}
