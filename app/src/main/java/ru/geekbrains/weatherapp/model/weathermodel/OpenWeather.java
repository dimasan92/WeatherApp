package ru.geekbrains.weatherapp.model.weathermodel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.geekbrains.weatherapp.model.weathermodel.data.WeatherRequest;

interface OpenWeather {
    @GET("data/2.5/weather")
    Call<WeatherRequest> loadWeather(@Query("q") String cityCountry,
                                     @Query("appid") String keyApi,
                                     @Query("units") String units);
}
