package ru.geekbrains.weatherapp.presenter.welcome;

import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.common.FragmentFactory;
import ru.geekbrains.weatherapp.model.citiesmodel.CitiesObserver;
import ru.geekbrains.weatherapp.presenter.Presenter;
import ru.geekbrains.weatherapp.view.IView;
import ru.geekbrains.weatherapp.view.screens.welcome.IWelcomeView;

public class WelcomePresenter extends Presenter implements IWelcomePresenter, CitiesObserver {

    private IWelcomeView mView;

    public static WelcomePresenter newInstance() {
        return new WelcomePresenter();
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
            FragmentFactory.showWeatherScreen(getActivity(), city);
        }
    }

    public void onMenuItemSettingsClick() {
        FragmentFactory.showSettingsDialog(Objects.requireNonNull(getActivity()));
    }

    @Override
    public void updateCities() {
        updateList();
    }

    private void updateList() {
        List<String> cities = new ArrayList<>(mModel.cities().getCities());
        mView.updateListView(cities);
    }
}
