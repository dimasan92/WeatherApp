package ru.geekbrains.weatherapp.presenter.welcome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.model.citiesmodel.CitiesObserver;
import ru.geekbrains.weatherapp.presenter.Presenter;
import ru.geekbrains.weatherapp.screens.FragmentFactory;
import ru.geekbrains.weatherapp.view.IView;
import ru.geekbrains.weatherapp.view.dialogs.settings.SettingsDialog;
import ru.geekbrains.weatherapp.view.screens.welcome.IWelcomeView;

public class WelcomePresenter extends Presenter implements IWelcomePresenter, CitiesObserver {

    private static final int REQUEST_PARAMS = 0;

    private IWelcomeView mView;

    public static WelcomePresenter newInstance() {
        WelcomePresenter presenter = new WelcomePresenter();

        Bundle bundle = new Bundle();
        bundle.putBoolean(Constants.PARAM_PRESSURE, true);
        bundle.putBoolean(Constants.PARAM_WIND, true);
        bundle.putBoolean(Constants.PARAM_HUMIDITY, true);
        presenter.setArguments(bundle);

        return presenter;
    }

    @Override
    public void assignModel(FragmentActivity activity) {
        super.assignModel(activity);
        mModel.citiesSubject().regCitiesObserver(this);
    }

    @Override
    public void attachView(IView view) {
        mView = (IWelcomeView) view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void viewIsReady() {
        updateList();
    }

    @Override
    public void onNewCityClick() {
        FragmentFactory.showNewCityDialog(Objects.requireNonNull(getActivity()));
    }

    @Override
    public void onListItemClick(String city) {
        String sensorsIndicationsSelected = Objects.requireNonNull(getActivity())
                .getResources().getString(R.string.weather_through_sensors);

        if (city.equals(sensorsIndicationsSelected)) {
            FragmentFactory.showSensorsIndicationsDialog(getActivity());
        } else {
            FragmentFactory.showWeatherScreen(getActivity(), createBundle(city));
        }
    }

    public void onMenuItemSettingsClick() {
        FragmentFactory.showSettingsDialog(Objects.requireNonNull(getActivity()), getArguments(),
                WelcomePresenter.this, REQUEST_PARAMS);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_PARAMS) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(Constants.PARAM_PRESSURE,
                    data.getBooleanExtra(Constants.PARAM_PRESSURE, true));
            bundle.putBoolean(Constants.PARAM_WIND,
                    data.getBooleanExtra(Constants.PARAM_WIND, true));
            bundle.putBoolean(Constants.PARAM_HUMIDITY,
                    data.getBooleanExtra(Constants.PARAM_HUMIDITY, true));
            setArguments(bundle);
        }
    }

    @Override
    public void updateCities() {
        updateList();
    }

    private void updateList() {
        List<String> cities = new ArrayList<>(mModel.cities().getCities());
        mView.updateListView(cities);
    }

    private Bundle createBundle(String city) {
        Bundle bundle = new Bundle();
        Bundle tmpBundle = getArguments();
        bundle.putString(Constants.CITY_NAME, city);
        if (tmpBundle != null) {
            bundle.putBoolean(Constants.PARAM_PRESSURE,
                    tmpBundle.getBoolean(Constants.PARAM_PRESSURE));
            bundle.putBoolean(Constants.PARAM_WIND,
                    tmpBundle.getBoolean(Constants.PARAM_WIND));
            bundle.putBoolean(Constants.PARAM_HUMIDITY,
                    tmpBundle.getBoolean(Constants.PARAM_HUMIDITY));
            return bundle;
        }
        return bundle;
    }
}
