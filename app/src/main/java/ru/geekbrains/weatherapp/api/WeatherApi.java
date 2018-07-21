package ru.geekbrains.weatherapp.api;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.model.currentweather.WeatherResponce;

public interface WeatherApi {

    @GET(Constants.CURRENT_WEATHER_URL)
    Single<WeatherResponce> loadCurrentWeather(@Query(Constants.QUERY_CITY) String city,
                                                @Query(Constants.QUERY_UNITS) String units,
                                                @Query(Constants.QUERY_APPID) String keyApi);
}
