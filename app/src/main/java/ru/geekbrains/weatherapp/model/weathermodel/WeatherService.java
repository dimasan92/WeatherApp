package ru.geekbrains.weatherapp.model.weathermodel;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ru.geekbrains.weatherapp.common.Constants;

public class WeatherService extends IntentService {

    private static final String OPEN_WEATHER_KEY = "480baf035826bf73a51c11f97d2faa17";
    private static final String UNIT_FORMAT_METRIC = "metric";

    public WeatherService() {
        super("WeatherService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(getUrl(intent));
        Request request = builder.build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                finishData("Ошибка загрузки");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                try {
                    String answer = null;
                    if (response.body() != null) {
                        answer = response.body().string();
                    }
                    finishData(answer);
                } catch (IOException e) {
                    finishData("Ошибка загрузки");
                }
            }
        });
    }

    @NonNull
    private String getUrl(Intent intent) {
        StringBuilder sb = new StringBuilder("https://api.openweathermap.org/data/2.5/weather?");
        sb = sb.append("q=").append(intent.getStringExtra(Constants.CITY_NAME))
                .append("&units=").append(UNIT_FORMAT_METRIC)
                .append("&APPID=").append(OPEN_WEATHER_KEY);
        return sb.toString();
    }

    private void finishData(String data) {
        sendResult(data, Constants.ACTION_WEATHER_SERVICE_FINISH,
                Constants.EXTRA_WEATHER_SERVICE_FINISH);
    }

    private void sendResult(String data, String action, String extra) {
        Intent intent = new Intent();
        intent.setAction(action);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra(extra, data);
        sendBroadcast(intent);
    }
}
