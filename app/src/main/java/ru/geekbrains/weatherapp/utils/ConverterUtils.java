package ru.geekbrains.weatherapp.utils;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.database.CitiesEntity;
import ru.geekbrains.weatherapp.model.listofcities.City;

public final class ConverterUtils {

    public static List<CitiesEntity> convertCityToEntity(List<City> cities, Context context) {
        List<CitiesEntity> entities = new ArrayList<>(cities.size());
        for (City city : cities) {
            String coordinates = context.getString(R.string.coordinate_string,
                    city.getCoord().getLat(), city.getCoord().getLon());
            entities.add(new CitiesEntity(city.getName(), city.getId(), city.getMain().getTemperature(),
                    city.getWeather()[0].getDescription(), coordinates));
        }
        return entities;
    }

    public static float convertCelsiusToFahrenheit(float degree) {
        return 1.8f * degree + 32;
    }
}