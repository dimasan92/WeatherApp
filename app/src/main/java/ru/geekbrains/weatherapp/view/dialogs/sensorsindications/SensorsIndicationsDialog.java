package ru.geekbrains.weatherapp.view.dialogs.sensorsindications;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.view.dialogs.DialogView;

public class SensorsIndicationsDialog extends DialogView implements ISensorsIndicationsDialog {

    private TextView mTempTextView;
    private TextView mPressTextView;
    private TextView mHumTextVIew;

    public static SensorsIndicationsDialog newInstance() {
        return new SensorsIndicationsDialog();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_sensors, null);

        mTempTextView = v.findViewById(R.id.tv_sensor_temperature);
        mPressTextView = v.findViewById(R.id.tv_tv_sensor_pressure);
        mHumTextVIew = v.findViewById(R.id.tv_sensor_humidity);

        mPresenter.sensorsIndications().viewIsReady();

        return new AlertDialog.Builder(Objects.requireNonNull(getActivity()))
                .setView(v)
                .setTitle(R.string.weather_through_sensors)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                })
                .create();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.sensorsIndications().dialogIsVisible();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.sensorsIndications().dialogIsInvisible();
    }

    @Override
    public void setTemperature(String temperature) {
        mTempTextView.setText(temperature);
    }

    @Override
    public void setPressure(String pressure) {
        mPressTextView.setText(pressure);
    }

    @Override
    public void setHumidity(String humidity) {
        mHumTextVIew.setText(humidity);
    }
}
