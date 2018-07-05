package ru.geekbrains.weatherapp.model.weathermodel;

import android.support.annotation.NonNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.geekbrains.weatherapp.model.weathermodel.data.WeatherRequest;

public class WeatherData implements IWeatherData {
    private static final String BASE_URL = "http://api.openweathermap.org/";
    private static final String OPEN_WEATHER_KEY = "8e782f2c35efbfc706cc6a8da5b41613";
    private static final String UNIT_FORMAT_METRIC = "metric";

    private OpenWeather mOpenWeather;
    private WeatherRequest mRequest;

    public WeatherData() {
        init();
    }

    private void init() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(createInterceptorClient())
                .build();
        mOpenWeather = retrofit.create(OpenWeather.class);
    }

    private OkHttpClient createInterceptorClient(){
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        return builder.build();
    }

    @Override
    public void request(String city) {
        mOpenWeather.loadWeather(city, UNIT_FORMAT_METRIC, OPEN_WEATHER_KEY)
                .enqueue(new Callback<WeatherRequest>() {
                    @Override
                    public void onResponse(@NonNull Call<WeatherRequest> call,
                                           @NonNull Response<WeatherRequest> response) {
                        if (response.body() != null) {
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<WeatherRequest> call,
                                          @NonNull Throwable t) {
                    }
                });
    }

    @Override
    public int getCod() {
        if (mRequest == null) {
            return 0;
        }
        return mRequest.getCod();
    }

    @Override
    public float getTemp() {
        if (mRequest == null) {
            return 0f;
        }
        return mRequest.getMain().getTemp();
    }

    @Override
    public int getPressure() {
        if (mRequest == null) {
            return 0;
        }
        return mRequest.getMain().getPressure();
    }

    @Override
    public int getHumidity() {
        if (mRequest == null) {
            return 0;
        }
        return mRequest.getMain().getHumidity();
    }

    @Override
    public float getWindSpeed() {
        if (mRequest == null) {
            return 0;
        }
        return mRequest.getWind().getSpeed();
    }

    @Override
    public int getClouds() {
        if (mRequest == null) {
            return 0;
        }
        return mRequest.getClouds().getAll();
    }

    @Override
    public String getName() {
        if (mRequest == null) {
            return null;
        }
        return mRequest.getName();
    }
}
