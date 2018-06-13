package ru.geekbrains.weatherapp.fragments;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.Objects;

public abstract class CommonDialog extends DialogFragment {

    protected CommonPresenter mPresenter;

    protected abstract CommonPresenter createPresenter();

    protected void initPresenter(String tag) {

        FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        mPresenter = (CommonPresenter) fm.findFragmentByTag(tag);

        if (mPresenter == null) {
            mPresenter = createPresenter();
            mPresenter.assignModel(getActivity());
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(mPresenter, tag);
            ft.commit();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
