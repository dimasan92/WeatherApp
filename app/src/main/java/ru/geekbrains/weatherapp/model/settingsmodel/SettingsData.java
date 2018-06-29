package ru.geekbrains.weatherapp.model.settingsmodel;

import android.content.Context;
import android.content.SharedPreferences;

import ru.geekbrains.weatherapp.common.Constants;

public class SettingsData implements ISettingsData {
    private static final String SETTINGS_PREFERENCES = "settings_preferences";

    private SharedPreferences mSharedPreferences;

    public SettingsData(Context context) {
        mSharedPreferences = context.getSharedPreferences(SETTINGS_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public void saveIndState(boolean wind, boolean pressure, boolean humidity) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        editor.putBoolean(Constants.PARAM_WIND, wind);
        editor.putBoolean(Constants.PARAM_PRESSURE, pressure);
        editor.putBoolean(Constants.PARAM_HUMIDITY, humidity);

        editor.apply();
    }

    @Override
    public boolean getParamWind() {
        return mSharedPreferences.getBoolean(Constants.PARAM_WIND, true);
    }

    @Override
    public boolean getParamPressure() {
        return mSharedPreferences.getBoolean(Constants.PARAM_PRESSURE, true);
    }

    @Override
    public boolean getParamHumidity() {
        return mSharedPreferences.getBoolean(Constants.PARAM_HUMIDITY, true);
    }
}
