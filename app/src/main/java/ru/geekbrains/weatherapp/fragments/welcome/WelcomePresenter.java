package ru.geekbrains.weatherapp.fragments.welcome;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.common.Model;
import ru.geekbrains.weatherapp.fragments.choosecity.ChooseCityFragment;
import ru.geekbrains.weatherapp.fragments.weather.WeatherFragment;

public class WelcomePresenter extends Fragment {

    private WelcomeFragment mFragment;
    private Model mModel;

    public static WelcomePresenter newInstance() {
        return new WelcomePresenter();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void attachView(WelcomeFragment fragment) {
        mFragment = fragment;
    }

    public void detachView() {
        mFragment = null;
    }

    public void onTransitionClick() {
        if (emptyCityName()) {
            return;
        }

        replaceNewFragment(WeatherFragment.newInstance(createBundle()));
    }

    public void onChooseCityClick() {
        replaceNewFragment(ChooseCityFragment.newInstance(createBundle()));
    }

    public void onAddCityClick() {
        if (emptyCityName()) {
            return;
        }

        if (mModel.addCity(mFragment.getCityName())) {
            mFragment.makeToast(R.string.success_add_city);
        } else {
            mFragment.makeToast(R.string.fail_add_city);
        }
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

    private boolean emptyCityName() {
        if (mFragment.getCityName().trim().equals("")) {
            mFragment.makeToast(R.string.empty_city_name);
            return true;
        }
        return false;
    }

    private void replaceNewFragment(Fragment fragment) {
        if (getActivity() == null) {
            return;
        }

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_fragment, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    private Bundle createBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.CITY_NAME, mFragment.getCityName());
        bundle.putBoolean(Constants.PARAM_PRESSURE, mFragment.getPressureParam());
        bundle.putBoolean(Constants.PARAM_WIND, mFragment.getWindParam());
        bundle.putBoolean(Constants.PARAM_HUMIDITY, mFragment.getHumidityParam());
        return bundle;
    }
}
