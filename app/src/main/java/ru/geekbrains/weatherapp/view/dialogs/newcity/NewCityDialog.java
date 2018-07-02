package ru.geekbrains.weatherapp.view.dialogs.newcity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Objects;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.view.dialogs.DialogView;

public class NewCityDialog extends DialogView implements INewCityDialog {

    private TextInputEditText mCityNameEditText;

    public static NewCityDialog newInstance() {
        return new NewCityDialog();
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
            Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
            button.setOnClickListener((v1 -> mPresenter.newCity().onAddNewCityClick()));
        }));

        return newDialog;
    }

    @Override
    public String getEnteredText() {
        return mCityNameEditText.getText().toString();
    }

    @Override
    public void showError(int stringResource) {
        String message = Objects.requireNonNull(getActivity()).getResources()
                .getString(stringResource);
        mCityNameEditText.setError(message);
    }

    @Override
    public void hideError() {
        mCityNameEditText.setError(null);
    }

    @Override
    public void makeToast(int stringId) {
        Toast.makeText(Objects.requireNonNull(getActivity()).getApplicationContext(),
                stringId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void close() {
        super.dismiss();
    }
}
