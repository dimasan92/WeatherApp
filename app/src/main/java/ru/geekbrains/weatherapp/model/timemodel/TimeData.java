package ru.geekbrains.weatherapp.model.timemodel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeData implements ITimeData {

    @Override
    public String getDayOfWeek(Locale locale) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", locale);
        Date d = new Date();
        return sdf.format(d);
    }
    @Override
    public String getMonth(Locale locale) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM", locale);
        Date d = new Date();
        return sdf.format(d);
    }

    @Override
    public String getDayOfMonth(Locale locale) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd", locale);
        Date d = new Date();
        return sdf.format(d);
    }
}
