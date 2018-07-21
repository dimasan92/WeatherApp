package ru.geekbrains.weatherapp.screens.main.implementations;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.application.App;
import ru.geekbrains.weatherapp.screens.base.IToolbarSetter;
import ru.geekbrains.weatherapp.screens.main.contracts.IMainPresenter;
import ru.geekbrains.weatherapp.screens.main.contracts.IMainView;
import ru.geekbrains.weatherapp.screens.main.list.ForecastAdapter;

public class MainView extends Fragment implements IMainView, IToolbarSetter {

    @Inject
    IMainPresenter<IMainView> mPresenter;

    private Consumer<List<String>> mToolbarSetter;

//    private TextView mTvCityName;
//    private TextView mTvCurrentTemperature;
//    private TextView mTvDescription;
//    private TextView mTvWindTitle;
//    private TextView mTvWindValue;
//    private TextView mTvPressureTitle;
//    private TextView mTvPressureValue;
//    private TextView mTvHumidityTitle;
//    private TextView mTvHumidityValue;
//    private TextView mTvDate;
//    private TextView mTvDayOfWeek;

    public static MainView newInstance() {
        return new MainView();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getApp().getScreenComponent().inject(this);
        mPresenter.getDataForToolBar(mToolbarSetter);
        mPresenter.attachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.screen_weather, container, false);

        initList(layout);

//        mTvCityName = layout.findViewById(R.id.tv_name_of_city);
//        mTvCurrentTemperature = layout.findViewById(R.id.tv_current_temperature);
//        mTvDescription = layout.findViewById(R.id.tv_description);
//        mTvWindTitle = layout.findViewById(R.id.tv_title_wind);
//        mTvWindValue = layout.findViewById(R.id.tv_wind);
//        mTvPressureTitle = layout.findViewById(R.id.tv_title_pressure);
//        mTvPressureValue = layout.findViewById(R.id.tv_pressure);
//        mTvHumidityTitle = layout.findViewById(R.id.tv_title_humidity);
//        mTvHumidityValue = layout.findViewById(R.id.tv_humidity);
//        mTvDayOfWeek = layout.findViewById(R.id.tv_day_of_week);
//        mTvDate = layout.findViewById(R.id.tv_date);
//        mPresenter.viewIsReady(Objects.requireNonNull(getActivity()).getApplicationContext());

        return layout;
    }

    @Override
    public void getDataForToolbar(Consumer<List<String>> toolbarSetter) {
        mToolbarSetter = toolbarSetter;
    }

    private void initList(View layout){
        final RecyclerView recyclerView = layout.findViewById(R.id.rw_forecast_weather);
        recyclerView.setLayoutManager(getListLayoutManager());
        ForecastAdapter adapter = new ForecastAdapter();
        recyclerView.setAdapter(adapter);
    }

    private LinearLayoutManager getListLayoutManager(){
        LinearLayoutManager layoutManager;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new LinearLayoutManager(
                    getActivity(), LinearLayoutManager.HORIZONTAL, false);
        } else {
            layoutManager = new LinearLayoutManager(
                    getActivity(), LinearLayoutManager.VERTICAL, false);
        }
        return layoutManager;
    }

//    @Override
//    public void setVisibilityWindParam(boolean visible) {
//        if (visible) {
//            mTvWindTitle.setVisibility(View.VISIBLE);
//            mTvWindValue.setVisibility(View.VISIBLE);
//        }
//    }
//
//    @Override
//    public void setVisibilityPressureParam(boolean visible) {
//        if (visible) {
//            mTvPressureTitle.setVisibility(View.VISIBLE);
//            mTvPressureValue.setVisibility(View.VISIBLE);
//        }
//    }
//
//    @Override
//    public void setVisibilityHumidityParam(boolean visible) {
//        if (visible) {
//            mTvHumidityTitle.setVisibility(View.VISIBLE);
//            mTvHumidityValue.setVisibility(View.VISIBLE);
//        }
//    }
//
//    @Override
//    public void setCity(String cityName) {
//        mTvCityName.setText(cityName);
//    }
//
//
//    @Override
//    public void setDate(String date) {
//        mTvDate.setText(date);
//    }
//
//    @Override
//    public void setDayOfWeek(String day) {
//        mTvDayOfWeek.setText(day);
//    }
//
//    @Override
//    public void setCurrentTemperature(String temperature) {
//        mTvCurrentTemperature.setText(temperature);
//    }
//
//    @Override
//    public void setDescription(String description) {
//        mTvDescription.setText(description);
//    }
//
//    @Override
//    public void setPressure(String value) {
//        mTvPressureValue.setText(value);
//    }
//
//    @Override
//    public void setHumidity(String value) {
//        mTvHumidityValue.setText(value);
//    }
//
//    @Override
//    public void setWind(String value) {
//        mTvWindValue.setText(value);
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
