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
        if(intent.getBooleanExtra(WelcomeActivity.WIND, true)){
            tvWindTitle.setVisibility(View.VISIBLE);
            tvWind.setVisibility(View.VISIBLE);
        }else{
            tvWindTitle.setVisibility(View.GONE);
            tvWind.setVisibility(View.GONE);
        }
        if(intent.getBooleanExtra(WelcomeActivity.PRESSURE, true)){
            tvPressureTitle.setVisibility(View.VISIBLE);
            tvPressure.setVisibility(View.VISIBLE);
        }else{
            tvPressureTitle.setVisibility(View.GONE);
            tvPressure.setVisibility(View.GONE);
        }
        if(intent.getBooleanExtra(WelcomeActivity.HUMIDITY, true)){
            tvHumidityTitle.setVisibility(View.VISIBLE);
            tvHumidity.setVisibility(View.VISIBLE);
        }else{
            tvHumidityTitle.setVisibility(View.GONE);
            tvHumidity.setVisibility(View.GONE);
        }
    }
}
