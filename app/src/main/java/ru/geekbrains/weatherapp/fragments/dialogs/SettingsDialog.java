package ru.geekbrains.weatherapp.fragments.dialogs;


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
import ru.geekbrains.weatherapp.fragments.CommonDialog;

public class SettingsDialog extends CommonDialog {

    private CheckBox mCbPressure;
    private CheckBox mCbWind;
    private CheckBox mCbHumidity;

    public static SettingsDialog newInstance() {
        return new SettingsDialog();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_settings, null);

        mCbPressure = v.findViewById(R.id.cb_pressure);
        mCbWind = v.findViewById(R.id.cb_wind);
        mCbHumidity = v.findViewById(R.id.cb_humidity);

        return new AlertDialog.Builder(Objects.requireNonNull(getActivity()))
                .setView(v)
                .setTitle(R.string.settings_dialog_title)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    mPresenter.onParamsChooseClick();
                })
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> {
                })
                .create();
    }

    public boolean getPressureParam() {
        return mCbPressure.isChecked();
    }

    public boolean getWindParam() {
        return mCbWind.isChecked();
    }

    public boolean getHumidityParam() {
        return mCbHumidity.isChecked();
    }

    public void sendResult(int resultCode, Intent intent) {
        if (getTargetFragment() == null) {
            return;
        }

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
