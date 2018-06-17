package ru.geekbrains.weatherapp.fragments.dialogs;

import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

import java.util.regex.Pattern;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.fragments.base.AbstractDialog;
import ru.geekbrains.weatherapp.fragments.base.AbstractPresenter;

public class DialogPresenter extends AbstractPresenter {

    Pattern checkCityName = Pattern.compile("^[А-ЯA-Z][а-яa-z]+$");

    public static DialogPresenter newInstance() {
        return new DialogPresenter();
    }

    public void onConfirmAddClick() {

        if (incorrectCityName()) {
            return;
        }

        if (mModel.addCity(((AddCityDialog) mScreen).getEnteredText())) {
            ((AbstractDialog) mScreen).makeToast(R.string.success_add_city);
            ((AddCityDialog) mScreen).dismiss();
        } else {
            ((AbstractDialog) mScreen).makeToast(R.string.fail_add_city);
        }
    }

    public void onParamsChooseClick() {
        Intent intent = new Intent();
        intent.putExtra(Constants.PARAM_PRESSURE, ((SettingsDialog) mScreen).getPressureParam());
        intent.putExtra(Constants.PARAM_WIND, ((SettingsDialog) mScreen).getWindParam());
        intent.putExtra(Constants.PARAM_HUMIDITY, ((SettingsDialog) mScreen).getHumidityParam());
        ((SettingsDialog) mScreen).sendResult(Activity.RESULT_OK, intent);
    }

    private boolean incorrectCityName() {
        String enteredText = ((AddCityDialog) mScreen).getEnteredText();
        if (checkCityName.matcher(enteredText).matches()) {
            ((AddCityDialog) mScreen).hideError();
            return false;
        }
        ((AddCityDialog) mScreen).showError(R.string.incorrect_city_name);
        return true;
    }
}
