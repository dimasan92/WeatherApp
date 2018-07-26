package ru.geekbrains.weatherapp.di.application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.geekbrains.weatherapp.api.WeatherApi;

import static ru.geekbrains.weatherapp.common.Constants.BASE_URL;

@Module
public class WeatherApiModule {

    @Singleton
    @Provides
    WeatherApi provideWeatherApi(GsonConverterFactory gFactory, RxJava2CallAdapterFactory rxFactory,
                                 OkHttpClient interceptorClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(gFactory)
                .addCallAdapterFactory(rxFactory)
                .client(interceptorClient)
                .build();
        return retrofit.create(WeatherApi.class);
    }
}
