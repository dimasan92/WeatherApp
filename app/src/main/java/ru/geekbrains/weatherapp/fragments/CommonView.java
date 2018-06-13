package ru.geekbrains.weatherapp.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.Objects;

public abstract class CommonView extends Fragment {

    protected CommonPresenter mCommonPresenter;

    protected abstract CommonPresenter createPresenter();

    protected void initPresenter(String tag) {
        FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        mCommonPresenter = (CommonPresenter) fm.findFragmentByTag(tag);

        if (mCommonPresenter == null) {
            mCommonPresenter = createPresenter();
            mCommonPresenter.assignModel(getActivity());
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(mCommonPresenter, tag);
            ft.commit();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCommonPresenter.detachView();
    }
}
