package ru.geekbrains.weatherapp.view.screens.weather;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import ru.geekbrains.weatherapp.model.weathermodel.WeatherService;
import ru.geekbrains.weatherapp.presenter.weather.IWeatherPresenter;
import ru.geekbrains.weatherapp.presenter.weather.WeatherPresenter;

public class WeatherView extends Fragment implements IWeatherView {

    private IWeatherPresenter mPresenter;

    private TextView mTvCityName;
    private TextView mTvWindTitle;
    private TextView mTvWindValue;
    private TextView mTvPressureTitle;
    private TextView mTvPressureValue;
    private TextView mTvHumidityTitle;
    private TextView mTvHumidityValue;
    private TextView mTvDate;
    private TextView mTvDayOfWeek;

    private FinishReceiver mFinishReceiver;
    private UpdateReceiver mUpdateReceiver;

    public static WeatherView newInstance(Bundle bundle) {
        WeatherView view = new WeatherView();
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
    public void setCity(String cityName) {
        mTvCityName.setText(cityName);
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
    public void setDate(String date) {
        mTvDate.setText(date);
    }

    @Override
    public void setDayOfWeek(String day) {
        mTvDayOfWeek.setText(day);
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
    public void startWeatherService() {
        Context context = Objects.requireNonNull(getActivity()).getApplicationContext();
        Intent intent = new Intent(context, WeatherService.class);
        context.startService(intent);
    }

    @Override
    public void registerReceivers() {
        mFinishReceiver = new FinishReceiver();
        mUpdateReceiver = new UpdateReceiver();
        Context context = Objects.requireNonNull(getActivity()).getApplicationContext();

        IntentFilter intentFilter = new IntentFilter(
                Constants.ACTION_WEATHER_SERVICE_FINISH);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        context.registerReceiver(mFinishReceiver, intentFilter);

        IntentFilter updateIntentFilter = new IntentFilter(
                Constants.ACTION_WEATHER_SERVICE_UPDATE);
        updateIntentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        context.registerReceiver(mUpdateReceiver, updateIntentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    public class FinishReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            mPresenter.weatherDataUpdated(intent);
        }
    }

    public class UpdateReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            mPresenter.weatherDataFinished(intent);
        }
    }
}
