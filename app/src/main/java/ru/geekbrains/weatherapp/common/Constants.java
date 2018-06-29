package ru.geekbrains.weatherapp.common;

public class Constants {

    // service constants
    public static final String ACTION_WEATHER_SERVICE_UPDATE = "weather_service_update_action";
    public static final String ACTION_WEATHER_SERVICE_FINISH = "weather_service_finish_action";
    public static final String EXTRA_WEATHER_SERVICE_UPDATE = "weather_service_update_extra";
    public static final String EXTRA_WEATHER_SERVICE_FINISH = "weather_service_finish_extra";

    // params
    public static final String CITY_NAME = "city_name";
    public static final String PARAM_PRESSURE = "param_pressure";
    public static final String PARAM_WIND = "param_wind";
    public static final String PARAM_HUMIDITY = "param_humidity";

    // cities set preference
    public static final String SAVED_SET_OF_CITIES = "saved_set_of_cities";

    // download
    public static final String DOWNLOAD_ERROR = "download error";
}
