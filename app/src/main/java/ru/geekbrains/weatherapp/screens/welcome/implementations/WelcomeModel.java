package ru.geekbrains.weatherapp.screens.welcome.implementations;

import ru.geekbrains.weatherapp.database.CitiesDao;
import ru.geekbrains.weatherapp.screens.welcome.contracts.IWelcomeModel;

public class WelcomeModel implements IWelcomeModel {

    private CitiesDao mDao;

//    private List<CitiesObserver> mObservers;

    public WelcomeModel(CitiesDao dao) {
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
