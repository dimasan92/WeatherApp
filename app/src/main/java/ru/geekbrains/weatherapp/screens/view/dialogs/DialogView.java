package ru.geekbrains.weatherapp.screens.view.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import java.util.Objects;

import ru.geekbrains.weatherapp.common.FragmentFactory;
import ru.geekbrains.weatherapp.screens.base.IView;
import ru.geekbrains.weatherapp.screens.presenter.dialog.IDialogPresenter;

public abstract class DialogView extends DialogFragment implements IView {

    protected IDialogPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = FragmentFactory.getDialogPresenter(Objects.requireNonNull(getActivity()));
//        mPresenter.assignModel(getActivity());
//        mPresenter.attachView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mPresenter.detachView();
    }
}