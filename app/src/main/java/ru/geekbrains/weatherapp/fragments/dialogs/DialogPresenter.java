package ru.geekbrains.weatherapp.fragments.dialogs;

import android.app.Activity;
import android.content.Intent;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.fragments.base.AbstractDialog;
import ru.geekbrains.weatherapp.fragments.base.AbstractPresenter;

public class DialogPresenter extends AbstractPresenter {

    public static DialogPresenter newInstance() {
        return new DialogPresenter();
    }

    public void onConfirmAddClick() {
        if (emptyCityName()) {
            return;
        }

        if (mModel.addCity(((AddCityDialog) mScreen).getCityName())) {
            ((AbstractDialog) mScreen).makeToast(R.string.success_add_city);
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

    private boolean emptyCityName() {
        if (((AddCityDialog) mScreen).getCityName().trim().equals("")) {
            ((AbstractDialog) mScreen).makeToast(R.string.empty_city_name);
            return true;
        }
        return false;
    }
}
