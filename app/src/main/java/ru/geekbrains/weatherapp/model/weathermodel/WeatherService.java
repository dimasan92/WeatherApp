package ru.geekbrains.weatherapp.model.weathermodel;

import android.app.IntentService;
import android.content.Intent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import ru.geekbrains.weatherapp.common.Constants;

public class WeatherService extends IntentService {

    public WeatherService() {
        super("WeatherService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        HttpsURLConnection urlConnection = null;
        try {
            URL url = new URL("https://geekbrains.ru/");
            urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            updateData("Подготовка данных");
            urlConnection.connect();
            updateData("Соединение");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder buf = new StringBuilder();
            updateData("Получение данных");

            String line = null;
            int numLine = 0;

            while ((line = in.readLine()) != null) {
                numLine++;
                updateData(String.format("Строка %d", numLine));
                buf.append(line);
            }
            finishData("Данные загружены");

        } catch (Exception e) {
            finishData("Ошибка загрузки");
        } finally {
            if (urlConnection != null) urlConnection.disconnect();
        }
    }

    private void updateData(String data) {
        sendResult(data, Constants.ACTION_WEATHER_SERVICE_UPDATE,
                Constants.EXTRA_WEATHER_SERVICE_UPDATE);
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
