package ru.geekbrains.weatherapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.common.Constants;

public final class PrefsUtils {

    private static final String SETTINGS_PREFERENCES = "settings_preferences";

    private PrefsUtils() {
    }

    public static void saveWindState(Context context, boolean isChecked) {
        SharedPreferences.Editor editor = getPref(context).edit();
        editor.putBoolean(Constants.PARAM_WIND, isChecked);
        editor.apply();
    }

    public static boolean getWindState(Context context) {
        return getPref(context).getBoolean(Constants.PARAM_WIND, true);
    }

    public static void savePressState(Context context, boolean isChecked) {
        SharedPreferences.Editor editor = getPref(context).edit();
        editor.putBoolean(Constants.PARAM_PRESSURE, isChecked);
        editor.apply();
    }

    public static boolean getPressState(Context context) {
        return getPref(context).getBoolean(Constants.PARAM_PRESSURE, true);
    }

    public static void saveHumState(Context context, boolean isChecked) {
        SharedPreferences.Editor editor = getPref(context).edit();
        editor.putBoolean(Constants.PARAM_HUMIDITY, isChecked);
        editor.apply();
    }

    public static boolean getHumState(Context context) {
        return getPref(context).getBoolean(Constants.PARAM_HUMIDITY, true);
    }

    public static void saveTempUnits(Context context, boolean isChecked) {
        SharedPreferences.Editor editor = getPref(context).edit();
        editor.putBoolean(Constants.PARAM_UNITS, isChecked);
        editor.apply();
    }

    public static boolean getTempUnits(Context context) {
        return getPref(context).getBoolean(Constants.PARAM_UNITS, false);
    }

    public static void saveShowSensorsState(Context context, boolean isChecked) {
        SharedPreferences.Editor editor = getPref(context).edit();
        editor.putBoolean(Constants.PARAM_SENSORS, isChecked);
        editor.apply();
    }

    public static boolean getShowSensorsState(Context context) {
        return getPref(context).getBoolean(Constants.PARAM_SENSORS, false);
    }

    private static SharedPreferences getPref(Context context) {
        return context.getSharedPreferences(SETTINGS_PREFERENCES, Context.MODE_PRIVATE);
    }
}
