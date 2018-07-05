package ru.geekbrains.weatherapp.view.screens.weather;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.common.FragmentFactory;
import ru.geekbrains.weatherapp.presenter.weather.IWeatherPresenter;

public class WeatherView extends Fragment implements IWeatherView {

    private IWeatherPresenter mPresenter;

    private TextView mTvCityName;
    private TextView mTvCurrentTemperature;
    private TextView mTvDescription;
    private TextView mTvWindTitle;
    private TextView mTvWindValue;
    private TextView mTvPressureTitle;
    private TextView mTvPressureValue;
    private TextView mTvHumidityTitle;
    private TextView mTvHumidityValue;
    private TextView mTvDate;
    private TextView mTvDayOfWeek;

    public static WeatherView newInstance(String cityName) {
        WeatherView view = new WeatherView();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.CITY_NAME, cityName);
        view.setArguments(bundle);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = FragmentFactory
                .getWeatherPresenter(Objects.requireNonNull(getActivity()), getArguments());
        mPresenter.assignModel(getActivity());
        mPresenter.attachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_weather, container, false);

        mTvCityName = layout.findViewById(R.id.tv_city);
        mTvCurrentTemperature = layout.findViewById(R.id.current_weather);
        mTvDescription = layout.findViewById(R.id.tv_description);
        mTvWindTitle = layout.findViewById(R.id.tv_title_wind);
        mTvWindValue = layout.findViewById(R.id.tv_wind);
        mTvPressureTitle = layout.findViewById(R.id.tv_title_pressure);
        mTvPressureValue = layout.findViewById(R.id.tv_pressure);
        mTvHumidityTitle = layout.findViewById(R.id.tv_title_humidity);
        mTvHumidityValue = layout.findViewById(R.id.tv_humidity);
        mTvDayOfWeek = layout.findViewById(R.id.week_day);
        mTvDate = layout.findViewById(R.id.date);

        mPresenter.viewIsReady(Objects.requireNonNull(getActivity()).getApplicationContext());

        return layout;
    }

    @Override
    public void setVisibilityWindParam(boolean visible) {
        if (visible) {
            mTvWindTitle.setVisibility(View.VISIBLE);
            mTvWindValue.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setVisibilityPressureParam(boolean visible) {
        if (visible) {
            mTvPressureTitle.setVisibility(View.VISIBLE);
            mTvPressureValue.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setVisibilityHumidityParam(boolean visible) {
        if (visible) {
            mTvHumidityTitle.setVisibility(View.VISIBLE);
            mTvHumidityValue.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setCity(String cityName) {
        mTvCityName.setText(cityName);
    }


    @Override
    public void setDate(String date) {
        mTvDate.setText(date);
    }

    @Override
    public void setDayOfWeek(String day) {
        mTvDayOfWeek.setText(day);
    }

    @Override
    public void setCurrentTemperature(String temperature) {
        mTvCurrentTemperature.setText(temperature);
    }

    @Override
    public void setDescription(String description) {
        mTvDescription.setText(description);
    }

    @Override
    public void setPressure(String value) {
        mTvPressureValue.setText(value);
    }

    @Override
    public void setHumidity(String value) {
        mTvHumidityValue.setText(value);
    }

    @Override
    public void setWind(String value) {
        mTvWindValue.setText(value);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
