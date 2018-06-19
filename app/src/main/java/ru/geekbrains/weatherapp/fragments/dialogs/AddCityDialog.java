package ru.geekbrains.weatherapp.fragments.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.fragments.base.AbstractDialog;

public class AddCityDialog extends AbstractDialog {

    private TextInputEditText mCityNameEditText;

    public static AddCityDialog newInstance() {
        return new AddCityDialog();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_city, null);

        mCityNameEditText = v.findViewById(R.id.et_enter_city_name);

        Dialog newDialog = new AlertDialog.Builder(Objects.requireNonNull(getActivity()))
                .setView(v)
                .setTitle(R.string.add_city_dialog_title)
                .setPositiveButton(android.R.string.paste, null)
                .create();
        newDialog.setOnShowListener((dialog -> {
            Button button = ((AlertDialog)dialog).getButton(AlertDialog.BUTTON_POSITIVE);
            button.setOnClickListener((v1 -> {
                mPresenter.onConfirmAddClick();
            }));
        }));

        return newDialog;
    }

    public String getEnteredText() {
        return mCityNameEditText.getText().toString();
    }

    public void showError(int stringResource){
        String message = Objects.requireNonNull(getActivity()).getResources()
                .getString(R.string.incorrect_city_name);
        mCityNameEditText.setError(message);
    }

    public void hideError(){
        mCityNameEditText.setError(null);
    }
}
