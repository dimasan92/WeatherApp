package ru.geekbrains.weatherapp.utils;

import org.json.JSONException;
import org.json.JSONObject;

public class ParseWeatherUtils {

    private static final String COD = "cod";

    public static final int ERROR_CODE = 404;

    public static int getCod(String weather) {
        int cod = ERROR_CODE;
        try {
            JSONObject jsonObject = new JSONObject(weather);
            cod = jsonObject.getInt(COD);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cod;
    }
}
