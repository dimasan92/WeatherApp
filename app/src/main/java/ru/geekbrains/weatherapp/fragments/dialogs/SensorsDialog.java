package ru.geekbrains.weatherapp.fragments.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import java.util.Objects;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.fragments.base.AbstractDialog;

public class SensorsDialog extends AbstractDialog {

    public static SensorsDialog newInstance() {
        return new SensorsDialog();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_sensors, null);

        return new AlertDialog.Builder(Objects.requireNonNull(getActivity()))
                .setView(v)
                .setTitle(R.string.weather_through_sensors)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                })
                .create();
    }
}
