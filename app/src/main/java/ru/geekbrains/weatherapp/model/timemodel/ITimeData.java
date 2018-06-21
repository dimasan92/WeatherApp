package ru.geekbrains.weatherapp.model.timemodel;

import java.util.Locale;

public interface ITimeData {

    String getDayOfWeek(Locale locale);

    String getMonth(Locale locale);

    String getDayOfMonth(Locale locale);
}
