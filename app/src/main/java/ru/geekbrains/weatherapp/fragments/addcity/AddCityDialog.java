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
import ru.geekbrains.weatherapp.fragments.CommonDialog;
import ru.geekbrains.weatherapp.fragments.CommonPresenter;

public class AddCityDialog extends CommonDialog {

    private EditText mEtCityName;

    public static AddCityDialog newInstance() {
        return new AddCityDialog();
    }

    @Override
    protected CommonPresenter createPresenter() {
        return DialogPresenter.newInstance();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter(Constants.DIALOG_PRESENTER_TAG);
        mPresenter.attachView(this);
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
                    ((DialogPresenter)mPresenter).onConfirmAddClick();
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
