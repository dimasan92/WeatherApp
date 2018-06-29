package ru.geekbrains.weatherapp.view.dialogs.newcity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.model.weathermodel.WeatherService;
import ru.geekbrains.weatherapp.view.dialogs.DialogView;

public class NewCityDialog extends DialogView implements INewCityDialog {

    private TextInputEditText mCityNameEditText;
    private FinishReceiver mFinishReceiver;

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
    public void startWeatherService(String cityName) {
        Context context = Objects.requireNonNull(getActivity()).getApplicationContext();
        Intent intent = new Intent(context, WeatherService.class);
        intent.putExtra(Constants.CITY_NAME, cityName);
        context.startService(intent);
    }

    @Override
    public void close() {
        super.dismiss();
    }

    @Override
    public void registerReceiver() {
        mFinishReceiver = new FinishReceiver();
        Context context = Objects.requireNonNull(getActivity()).getApplicationContext();

        IntentFilter intentFilter = new IntentFilter(
                Constants.ACTION_WEATHER_SERVICE_FINISH);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        context.registerReceiver(mFinishReceiver, intentFilter);
    }

    @Override
    public void unregisterReceiver() {
        if (mFinishReceiver == null) {
            return;
        }
        Context context = Objects.requireNonNull(getActivity()).getApplicationContext();
        context.unregisterReceiver(mFinishReceiver);
    }

    public class FinishReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            mPresenter.newCity().weatherDataFinished(intent);
        }
    }

    @Override
    public void onDestroy() {
        mPresenter.newCity().viewIsDestroyed();
        super.onDestroy();
    }
}
