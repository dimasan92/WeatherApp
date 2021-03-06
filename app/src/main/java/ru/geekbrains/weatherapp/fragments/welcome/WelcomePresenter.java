package ru.geekbrains.weatherapp.fragments.welcome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.common.listener.Observer;
import ru.geekbrains.weatherapp.fragments.base.AbstractPresenter;
import ru.geekbrains.weatherapp.fragments.dialogs.AddCityDialog;
import ru.geekbrains.weatherapp.fragments.dialogs.SettingsDialog;
import ru.geekbrains.weatherapp.fragments.weather.WeatherFragment;

public class WelcomePresenter extends AbstractPresenter implements Observer{

    private static final int REQUEST_PARAMS = 0;

    public static WelcomePresenter newInstance() {
        return new WelcomePresenter();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_PARAMS) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(Constants.PARAM_PRESSURE,
                    data.getBooleanExtra(Constants.PARAM_PRESSURE, false));
            bundle.putBoolean(Constants.PARAM_WIND,
                    data.getBooleanExtra(Constants.PARAM_WIND, false));
            bundle.putBoolean(Constants.PARAM_HUMIDITY,
                    data.getBooleanExtra(Constants.PARAM_HUMIDITY, false));
            setArguments(bundle);
        }
    }

    @Override
    public void assignModel(FragmentActivity activity) {
        super.assignModel(activity);
        mModel.registerObserver(this);
    }

    @Override
    public void update() {
        updateList();
    }

    @Override
    public void viewIsReady() {
        updateList();
    }

    public void onMenuItemSettingsClick() {
        FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        SettingsDialog dialog = SettingsDialog.newInstance(getArguments());
        dialog.setTargetFragment(WelcomePresenter.this, REQUEST_PARAMS);
        dialog.show(fm, Constants.SETTINGS_DIALOG_TAG);
    }

    public void onListItemClick(String city) {
        FragmentTransaction ft = Objects.requireNonNull(getActivity())
                .getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_fragment, WeatherFragment
                .newInstance(createBundle(city)));
        ft.addToBackStack(null);
        ft.commit();
    }

    public void onAddCityClick() {
        if (getActivity() == null) {
            return;
        }

        FragmentManager fm = getActivity().getSupportFragmentManager();
        AddCityDialog dialog = AddCityDialog.newInstance();
        dialog.show(fm, Constants.ADD_CITY_DIALOG_TAG);
    }

    public void updateList() {
        List<String> cities = new ArrayList<>(mModel.getCities());
        ((WelcomeScreen) mScreen).updateListView(cities);
    }

    private Bundle createBundle(String city) {
        Bundle bundle = new Bundle();
        Bundle tmpBundle = getArguments();
        bundle.putString(Constants.CITY_NAME, city);
        if (tmpBundle != null) {
            bundle.putBoolean(Constants.PARAM_PRESSURE, tmpBundle.getBoolean(Constants.PARAM_PRESSURE));
            bundle.putBoolean(Constants.PARAM_WIND, tmpBundle.getBoolean(Constants.PARAM_WIND));
            bundle.putBoolean(Constants.PARAM_HUMIDITY, tmpBundle.getBoolean(Constants.PARAM_HUMIDITY));
            return bundle;
        }
        return bundle;
    }
}
