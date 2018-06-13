package ru.geekbrains.weatherapp.fragments.addcity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.common.Constants;

public class AddCityDialog extends DialogFragment {

    private AddCityPresenter mPresenter;

    private EditText mEtCityName;

    public static AddCityDialog newInstance() {
        return new AddCityDialog();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
        mPresenter.attachView(this);
    }

    private void initPresenter() {
        FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        mPresenter = (AddCityPresenter) fm.findFragmentByTag(Constants.ADD_CITY_PRESENTER_TAG);

        if (mPresenter == null) {
            mPresenter = AddCityPresenter.newInstance();
            mPresenter.assignModel(getActivity());
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(mPresenter, Constants.ADD_CITY_PRESENTER_TAG);
            ft.commit();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_city, null);

        mEtCityName = v.findViewById(R.id.et_enter_city_name);

        return new AlertDialog.Builder(Objects.requireNonNull(getActivity()))
                .setView(v)
                .setTitle(R.string.add_city_dialog_title)
                .setPositiveButton(android.R.string.paste, (dialog, which) -> {
                    mPresenter.onConfirmAddClick();
                })
                .create();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    public String getCityName() {
        return mEtCityName.getText().toString();
    }

    public void makeToast(int stringId) {
        Toast.makeText(Objects.requireNonNull(getActivity()).getApplicationContext(),
                stringId, Toast.LENGTH_SHORT).show();
    }
}
