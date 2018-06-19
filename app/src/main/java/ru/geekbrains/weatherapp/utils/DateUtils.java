package ru.geekbrains.weatherapp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static String getDayOfWeek(Locale locale) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", locale);
        Date d = new Date();
        return sdf.format(d);
    }

    public static String getMonth(Locale locale) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM", locale);
        Date d = new Date();
        return sdf.format(d);
    }

    public static String getDayOfMonth(Locale locale) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd", locale);
        Date d = new Date();
        return sdf.format(d);
    }
}
