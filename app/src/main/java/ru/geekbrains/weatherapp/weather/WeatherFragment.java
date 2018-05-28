package ru.geekbrains.weatherapp.weather;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.common.Constants;

public class WeatherFragment extends Fragment {

    WeatherPresenter mPresenter;

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

        TextView tvCity = layout.findViewById(R.id.tv_city);
        TextView tvWindTitle = layout.findViewById(R.id.tv_title_wind);
        TextView tvWind = layout.findViewById(R.id.tv_wind);
        TextView tvPressureTitle = layout.findViewById(R.id.tv_title_pressure);
        TextView tvPressure = layout.findViewById(R.id.tv_pressure);
        TextView tvHumidityTitle = layout.findViewById(R.id.tv_title_humidity);
        TextView tvHumidity = layout.findViewById(R.id.tv_humidity);

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
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        super.onCreate(savedInstanceState);
        initPresenter();
        mPresenter.attachView(this);
    }

    private void initPresenter() {
        if (getActivity() != null) {
            FragmentManager fm = getActivity().getSupportFragmentManager();
            mPresenter = (WeatherPresenter) fm.findFragmentByTag(Constants.WEATHER_PRESENTER_TAG);
            if (mPresenter == null) {
                mPresenter = WeatherPresenter.init();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(mPresenter, Constants.WEATHER_PRESENTER_TAG);
                ft.commit();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
