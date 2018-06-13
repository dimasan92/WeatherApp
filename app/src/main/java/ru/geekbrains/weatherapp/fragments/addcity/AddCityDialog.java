package ru.geekbrains.weatherapp.fragments.addcity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import java.util.Objects;

import ru.geekbrains.weatherapp.R;

public class AddCityDialog extends DialogFragment {

    public static AddCityDialog newInstance() {
        return new AddCityDialog();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        
    }
}
