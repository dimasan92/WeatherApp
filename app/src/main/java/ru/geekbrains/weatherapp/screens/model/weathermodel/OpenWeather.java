package ru.geekbrains.weatherapp.screens.model.weathermodel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.geekbrains.weatherapp.model.currentweather.WeatherResponce;

interface OpenWeather {
    @GET("data/2.5/weather")
    Call<WeatherResponce> loadWeather(@Query("q") String city,
                                      @Query("units") String units,
                                      @Query("appid") String keyApi);
}
