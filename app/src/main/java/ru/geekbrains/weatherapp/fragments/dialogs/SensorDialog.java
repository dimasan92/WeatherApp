package ru.geekbrains.weatherapp.fragments.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.fragments.base.AbstractDialog;

public class SensorDialog extends AbstractDialog {

    private TextView mTempTextView;
    private TextView mPressTextView;
    private TextView mHumTextVIew;

    public static SensorDialog newInstance() {
        return new SensorDialog();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_sensors, null);

        mTempTextView = v.findViewById(R.id.tv_sensor_temperature);
        mPressTextView = v.findViewById(R.id.tv_tv_sensor_pressure);
        mHumTextVIew = v.findViewById(R.id.tv_sensor_humidity);

        mPresenter.viewIsReady();

        return new AlertDialog.Builder(Objects.requireNonNull(getActivity()))
                .setView(v)
                .setTitle(R.string.weather_through_sensors)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                })
                .create();
    }

    public void setTemp(String temp) {
        mTempTextView.setText(temp);
    }

    public void setPress(String press) {
        mPressTextView.setText(press);
    }

    public void setHumm(String humm) {
        mHumTextVIew.setText(humm);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.sensorDialogIsVisible();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.sensorDialogIsUnVisible();
    }
}
