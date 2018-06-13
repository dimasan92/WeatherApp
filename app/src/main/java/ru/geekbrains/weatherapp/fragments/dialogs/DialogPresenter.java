package ru.geekbrains.weatherapp.fragments.dialogs;

import android.app.Activity;
import android.content.Intent;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.fragments.CommonDialog;
import ru.geekbrains.weatherapp.fragments.CommonPresenter;

public class DialogPresenter extends CommonPresenter {

    public static DialogPresenter newInstance() {
        return new DialogPresenter();
    }

    public void onConfirmAddClick() {
        if (emptyCityName()) {
            return;
        }

        if (mModel.addCity(((AddCityDialog) mView).getCityName())) {
            ((CommonDialog) mView).makeToast(R.string.success_add_city);
        } else {
            ((CommonDialog) mView).makeToast(R.string.fail_add_city);
        }
    }

    public void onParamsChooseClick() {
        Intent intent = new Intent();
        intent.putExtra(Constants.PARAM_PRESSURE, ((SettingsDialog) mView).getPressureParam());
        intent.putExtra(Constants.PARAM_WIND, ((SettingsDialog) mView).getWindParam());
        intent.putExtra(Constants.PARAM_HUMIDITY, ((SettingsDialog) mView).getHumidityParam());
        ((SettingsDialog) mView).sendResult(Activity.RESULT_OK, intent);
    }

    private boolean emptyCityName() {
        if (((AddCityDialog) mView).getCityName().trim().equals("")) {
            ((CommonDialog) mView).makeToast(R.string.empty_city_name);
            return true;
        }
        return false;
    }
}
