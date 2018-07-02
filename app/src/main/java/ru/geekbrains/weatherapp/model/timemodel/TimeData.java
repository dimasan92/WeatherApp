package ru.geekbrains.weatherapp.model.timemodel;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeData {

    public static String getDayOfWeek(Context context) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE",
                context.getResources().getConfiguration().locale);
        Date d = new Date();
        return sdf.format(d);
    }

    public static String getMonth(Context context) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM",
                context.getResources().getConfiguration().locale);
        Date d = new Date();
        return sdf.format(d);
    }

    public static String getDayOfMonth(Context context) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd",
                context.getResources().getConfiguration().locale);
        Date d = new Date();
        return sdf.format(d);
    }
}
