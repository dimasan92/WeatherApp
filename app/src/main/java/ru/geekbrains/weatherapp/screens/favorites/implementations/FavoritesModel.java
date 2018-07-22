package ru.geekbrains.weatherapp.screens.favorites.implementations;

import ru.geekbrains.weatherapp.database.CitiesDao;
import ru.geekbrains.weatherapp.screens.favorites.contracts.IFavoritesModel;

public class FavoritesModel implements IFavoritesModel {

    private CitiesDao mDao;

//    private List<CitiesObserver> mObservers;

    public FavoritesModel(CitiesDao dao) {
        mDao = dao;
//        mObservers = new ArrayList<>();
    }
//
//    @Override
//    public boolean addCity(String cityName) {
//        mCities.insert(new CitiesEntity(cityName));
//
//        notifyCitiesObservers();
//        return true;
//    }
//
//    @Override
//    public List<String> getCities() {
//        List<String> cities = new ArrayList<>();
//        for (CitiesEntity entity : mCities.getAll()) {
//            cities.add(entity.getName());
//        }
//        return cities;
//    }
//
//    @Override
//    public void regCitiesObserver(CitiesObserver o) {
//        mObservers.add(o);
//    }
//
//    @Override
//    public void unregCitiesObserver(CitiesObserver o) {
//        mObservers.remove(o);
//    }
//
//    @Override
//    public void notifyCitiesObservers() {
//        for (CitiesObserver o : mObservers) {
//            o.updateCities();
//        }
//    }
}
