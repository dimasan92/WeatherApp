package ru.geekbrains.weatherapp.screens.view.screens.weather;

import ru.geekbrains.weatherapp.screens.base.IView;

public interface IWeatherView extends IView {

    void setVisibilityWindParam(boolean visible);

    void setVisibilityPressureParam(boolean visible);

    void setVisibilityHumidityParam(boolean visible);

    void setDate(String date);

    void setDayOfWeek(String day);

    void setCity(String cityName);

    void setCurrentTemperature(String temperature);

    void setDescription(String description);

    void setPressure(String value);

    void setHumidity(String value);

    void setWind(String value);
}
