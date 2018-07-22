package ru.geekbrains.weatherapp.screens.favorites.implementations;

import java.util.List;

import io.reactivex.functions.Consumer;
import ru.geekbrains.weatherapp.screens.favorites.contracts.IFavoritesModel;
import ru.geekbrains.weatherapp.screens.favorites.contracts.IFavoritesPresenter;
import ru.geekbrains.weatherapp.screens.favorites.contracts.IFavoritesView;

public class FavoritesPresenter<T extends IFavoritesView> implements IFavoritesPresenter<T> {

    private T mView;
    private IFavoritesModel mModel;

    public FavoritesPresenter(IFavoritesModel model) {
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
    public void getDataForToolBar(Consumer<List<String>> toolbarSetter) {

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
