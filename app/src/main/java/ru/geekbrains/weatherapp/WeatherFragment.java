package ru.geekbrains.weatherapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static ru.geekbrains.weatherapp.WelcomeFragment.CITY_NAME;
import static ru.geekbrains.weatherapp.WelcomeFragment.HUMIDITY_PARAM;
import static ru.geekbrains.weatherapp.WelcomeFragment.PRESSURE_PARAM;
import static ru.geekbrains.weatherapp.WelcomeFragment.WIND_PARAM;

public class WeatherFragment extends Fragment {

    public static WeatherFragment createNewInstance(Bundle bundle) {
        WeatherFragment fragment = new WeatherFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_weather, container, false);

        TextView tvCity = layout.findViewById(R.id.tv_city);
        TextView tvWindTitle = layout.findViewById(R.id.tv_title_wind);
        TextView tvWind = layout.findViewById(R.id.tv_wind);
        TextView tvPressureTitle = layout.findViewById(R.id.tv_title_pressure);
        TextView tvPressure = layout.findViewById(R.id.tv_pressure);
        TextView tvHumidityTitle = layout.findViewById(R.id.tv_title_humidity);
        TextView tvHumidity = layout.findViewById(R.id.tv_humidity);

        Bundle bundle = getArguments();

        tvCity.setText(bundle.getString(CITY_NAME));
        if(bundle.getBoolean(WIND_PARAM, false)){
            tvWindTitle.setVisibility(View.VISIBLE);
            tvWind.setVisibility(View.VISIBLE);
        }
        if(bundle.getBoolean(PRESSURE_PARAM, false)){
            tvPressureTitle.setVisibility(View.VISIBLE);
            tvPressure.setVisibility(View.VISIBLE);
        }
        if(bundle.getBoolean(HUMIDITY_PARAM, false)){
            tvHumidityTitle.setVisibility(View.VISIBLE);
            tvHumidity.setVisibility(View.VISIBLE);
        }
        return layout;
    }

}
