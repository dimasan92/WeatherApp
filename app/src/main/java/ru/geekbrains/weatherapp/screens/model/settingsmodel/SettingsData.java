package ru.geekbrains.weatherapp.model.settingsmodel;

import android.content.Context;
import android.content.SharedPreferences;

import ru.geekbrains.weatherapp.common.Constants;

public final class SettingsData {
    private static final String SETTINGS_PREFERENCES = "settings_preferences";

    private SettingsData() {
    }

    public static void saveIndState(Context context, boolean wind, boolean pressure, boolean humidity) {
        SharedPreferences.Editor editor = getPref(context).edit();

        editor.putBoolean(Constants.PARAM_WIND, wind);
        editor.putBoolean(Constants.PARAM_PRESSURE, pressure);
        editor.putBoolean(Constants.PARAM_HUMIDITY, humidity);

        editor.apply();
    }

    public static boolean getParamWind(Context context) {
        return getPref(context).getBoolean(Constants.PARAM_WIND, true);
    }

    public static boolean getParamPressure(Context context) {
        return getPref(context).getBoolean(Constants.PARAM_PRESSURE, true);
    }

    public static boolean getParamHumidity(Context context) {
        return getPref(context).getBoolean(Constants.PARAM_HUMIDITY, true);
    }

    private static SharedPreferences getPref(Context context) {
        return context.getSharedPreferences(SETTINGS_PREFERENCES, Context.MODE_PRIVATE);
    }
}
