package ru.geekbrains.weatherapp.screens.welcome.implementations;

import ru.geekbrains.weatherapp.screens.welcome.contracts.IWelcomeModel;
import ru.geekbrains.weatherapp.screens.welcome.contracts.IWelcomePresenter;
import ru.geekbrains.weatherapp.screens.welcome.contracts.IWelcomeView;

public class WelcomePresenter<T extends IWelcomeView> implements IWelcomePresenter<T>{

    private T mView;
    private IWelcomeModel mModel;

    public WelcomePresenter(IWelcomeModel model) {
        mModel = model;
    }


    @Override
    public void attachView(T view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void viewIsReady() {
        getCitiesList();
    }

//    @Override
//    public void onNewCityClick() {
//        FragmentFactory.showNewCityDialog(Objects.requireNonNull(getActivity()));
//    }
//
//    @Override
//    public void onListItemClick(String city) {
//        String sensorsIndicationsSelected = Objects.requireNonNull(getActivity())
//                .getResources().getString(R.string.weather_through_sensors);
//
//        if (city.equals(sensorsIndicationsSelected)) {
//            FragmentFactory.showSensorsIndicationsDialog(getActivity());
//        } else {
//            FragmentFactory.showWeatherScreen(getActivity(), city);
//        }
//    }
//
//    public void onMenuItemSettingsClick() {
//        FragmentFactory.showSettingsDialog(Objects.requireNonNull(getActivity()));
//    }
////
////    @Override
////    public void updateCities() {
////        updateList();
////    }

    private void getCitiesList() {
//        List<String> cities = mModel.cities().getCities();
//        mView.updateListView(cities);
    }
}
