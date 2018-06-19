package ru.geekbrains.weatherapp.fragments.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import java.util.Objects;

import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.fragments.dialogs.DialogPresenter;

public abstract class AbstractDialog extends DialogFragment {

    protected DialogPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
        mPresenter.attachView(this);
    }

    protected void initPresenter() {
        FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        mPresenter = (DialogPresenter) fm.findFragmentByTag(Constants.DIALOG_PRESENTER_TAG);

        if (mPresenter == null) {
            mPresenter = DialogPresenter.newInstance();
            mPresenter.assignModel(getActivity());
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(mPresenter, Constants.DIALOG_PRESENTER_TAG);
            ft.commit();
        }
    }

    public void makeToast(int stringId) {
        Toast.makeText(Objects.requireNonNull(getActivity()).getApplicationContext(),
                stringId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
