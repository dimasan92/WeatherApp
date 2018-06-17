package ru.geekbrains.weatherapp.fragments.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.Objects;

public abstract class AbstarctScreen extends Fragment {

    protected AbstractPresenter mPresenter;

    protected abstract AbstractPresenter createPresenter();

    protected void initPresenter(String tag) {
        FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        mPresenter = (AbstractPresenter) fm.findFragmentByTag(tag);

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
