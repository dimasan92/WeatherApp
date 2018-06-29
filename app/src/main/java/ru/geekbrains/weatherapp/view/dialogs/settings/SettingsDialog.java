package ru.geekbrains.weatherapp.view.dialogs.settings;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

import java.util.Objects;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.view.dialogs.DialogView;

public class SettingsDialog extends DialogView implements ISettingsDialog {

    private CheckBox mPressureCheckBox;
    private CheckBox mCbWindCheckBox;
    private CheckBox mCbHumidityCheckBox;

    public static SettingsDialog newInstance() {
        return new SettingsDialog();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_settings, null);

        mPressureCheckBox = v.findViewById(R.id.cb_pressure);
        mCbWindCheckBox = v.findViewById(R.id.cb_wind);
        mCbHumidityCheckBox = v.findViewById(R.id.cb_humidity);

        mPresenter.settings().viewIsReady();

        return new AlertDialog.Builder(Objects.requireNonNull(getActivity()))
                .setView(v)
                .setTitle(R.string.settings_dialog_title)
                .setPositiveButton(android.R.string.ok, (dialog, which) ->
                        mPresenter.settings().onParamsChooseClick())
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> {
                })
                .create();
    }

    @Override
    public boolean getPressureParam() {
        return mPressureCheckBox.isChecked();
    }

    @Override
    public boolean getWindParam() {
        return mCbWindCheckBox.isChecked();
    }

    @Override
    public boolean getHumidityParam() {
        return mCbHumidityCheckBox.isChecked();
    }

    @Override
    public void setWindParam(boolean paramIsSet) {
        mCbWindCheckBox.setChecked(paramIsSet);
    }

    @Override
    public void setPressureParam(boolean paramIsSet) {
        mPressureCheckBox.setChecked(paramIsSet);
    }

    @Override
    public void setHumidityParam(boolean paramIsSet) {
        mCbHumidityCheckBox.setChecked(paramIsSet);
    }
}
