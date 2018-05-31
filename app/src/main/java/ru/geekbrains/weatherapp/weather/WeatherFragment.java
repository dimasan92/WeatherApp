package ru.geekbrains.weatherapp.weather;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.utils.DateUtil;

public class WeatherFragment extends Fragment {

    private WeatherPresenter mPresenter;
    private FragmentActivity currentActivity;

    public static WeatherFragment init(Bundle bundle) {
        WeatherFragment fragment = new WeatherFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_weather, container, false);

        setBeginVisibleParams(layout);
        setDate(layout);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        super.onCreate(savedInstanceState);
        currentActivity = getActivity();
        initPresenter();
        mPresenter.attachView(this);
    }

    private void initPresenter() {
        FragmentManager fm = currentActivity.getSupportFragmentManager();
        mPresenter = (WeatherPresenter) fm.findFragmentByTag(Constants.WEATHER_PRESENTER_TAG);
        mPresenter = WeatherPresenter.init();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(mPresenter, Constants.WEATHER_PRESENTER_TAG);
        ft.commit();
    }

    private void setBeginVisibleParams(View layout) {
        final TextView tvCity = layout.findViewById(R.id.tv_city);
        final TextView tvWindTitle = layout.findViewById(R.id.tv_title_wind);
        final TextView tvWind = layout.findViewById(R.id.tv_wind);
        final TextView tvPressureTitle = layout.findViewById(R.id.tv_title_pressure);
        final TextView tvPressure = layout.findViewById(R.id.tv_pressure);
        final TextView tvHumidityTitle = layout.findViewById(R.id.tv_title_humidity);
        final TextView tvHumidity = layout.findViewById(R.id.tv_humidity);

        Bundle bundle = getArguments();

        if (bundle != null) {
            tvCity.setText(bundle.getString(Constants.CITY_NAME));
            if (bundle.getBoolean(Constants.PARAM_WIND, false)) {
                tvWindTitle.setVisibility(View.VISIBLE);
                tvWind.setVisibility(View.VISIBLE);
            }
            if (bundle.getBoolean(Constants.PARAM_PRESSURE, false)) {
                tvPressureTitle.setVisibility(View.VISIBLE);
                tvPressure.setVisibility(View.VISIBLE);
            }
            if (bundle.getBoolean(Constants.PARAM_HUMIDITY, false)) {
                tvHumidityTitle.setVisibility(View.VISIBLE);
                tvHumidity.setVisibility(View.VISIBLE);
            }
        }
    }

    private void setDate(View layout) {
        final TextView weekDay = layout.findViewById(R.id.week_day);
        final TextView date = layout.findViewById(R.id.date);
        Locale locale = getResources().getConfiguration().locale;
        weekDay.setText(DateUtil.getDayOfWeek(locale));
        date.setText(String.format("%s %s", DateUtil.getDayOfMonth(locale), DateUtil.getMonth(locale)));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
