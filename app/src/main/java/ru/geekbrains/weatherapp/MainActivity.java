package ru.geekbrains.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        setContentView(R.layout.activity_main);
        TextView tvCity = findViewById(R.id.tv_city);
        TextView tvWindTitle = findViewById(R.id.tv_title_wind);
        TextView tvWind = findViewById(R.id.tv_wind);
        TextView tvPressureTitle = findViewById(R.id.tv_title_pressure);
        TextView tvPressure = findViewById(R.id.tv_pressure);
        TextView tvHumidityTitle = findViewById(R.id.tv_title_humidity);
        TextView tvHumidity = findViewById(R.id.tv_humidity);

        tvCity.setText(intent.getStringExtra(WelcomeActivity.CITY_NAME));
        if(intent.getBooleanExtra(WelcomeActivity.WIND, false)){
            tvWindTitle.setVisibility(View.VISIBLE);
            tvWind.setVisibility(View.VISIBLE);
        }
        if(intent.getBooleanExtra(WelcomeActivity.PRESSURE, false)){
            tvPressureTitle.setVisibility(View.VISIBLE);
            tvPressure.setVisibility(View.VISIBLE);
        }
        if(intent.getBooleanExtra(WelcomeActivity.HUMIDITY, false)){
            tvHumidityTitle.setVisibility(View.VISIBLE);
            tvHumidity.setVisibility(View.VISIBLE);
        }
    }
}
