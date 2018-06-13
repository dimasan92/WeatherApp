package ru.geekbrains.weatherapp.fragments.welcome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.Set;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.common.Model;
import ru.geekbrains.weatherapp.fragments.CommonPresenter;
import ru.geekbrains.weatherapp.fragments.addcity.AddCityDialog;
import ru.geekbrains.weatherapp.fragments.weather.WeatherFragment;

public class WelcomePresenter extends CommonPresenter {
    private WelcomeView mFragment;
    private Model mModel;

    public static WelcomePresenter newInstance() {
        return new WelcomePresenter();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void attachView(WelcomeView fragment) {
        mFragment = fragment;
    }

    public void detachView() {
        mFragment = null;
    }

    public void viewIsReady() {
        Set<String> setOfCities = mModel.getCities();
        String[] cities = setOfCities.toArray(new String[setOfCities.size()]);
        mFragment.updateListView(cities);
    }

    public void onItemClick(String city) {
        if (getActivity() == null) {
            return;
        }

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
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

    public void assignModel(FragmentActivity activity) {

        FragmentManager fm = activity.getSupportFragmentManager();
        mModel = (Model) fm.findFragmentByTag(Constants.MODEL_TAG);

        if (mModel == null) {
            mModel = Model.newInstance();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(mModel, Constants.MODEL_TAG);
            ft.commit();
        }
    }

    private Bundle createBundle(String city) {
        Bundle bundle = new Bundle();
        Bundle tmpBundle = mFragment.getArguments();
        if (tmpBundle != null) {
            bundle.putString(Constants.CITY_NAME, city);
            bundle.putBoolean(Constants.PARAM_PRESSURE, tmpBundle.getBoolean(Constants.PARAM_PRESSURE));
            bundle.putBoolean(Constants.PARAM_WIND, tmpBundle.getBoolean(Constants.PARAM_WIND));
            bundle.putBoolean(Constants.PARAM_HUMIDITY, tmpBundle.getBoolean(Constants.PARAM_HUMIDITY));
            return bundle;
        }
        return bundle;
    }
}
