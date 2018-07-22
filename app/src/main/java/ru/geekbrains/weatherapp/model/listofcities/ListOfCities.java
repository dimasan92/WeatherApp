package ru.geekbrains.weatherapp.model.listofcities;

import com.google.gson.annotations.SerializedName;

import ru.geekbrains.weatherapp.model.Coord;
import ru.geekbrains.weatherapp.model.Main;
import ru.geekbrains.weatherapp.model.Sys;
import ru.geekbrains.weatherapp.model.Weather;

public final class ListOfCities {

    @SerializedName("id")
    private final long id;

    @SerializedName("name")
    private final String name;

    @SerializedName("coord")
    private final Coord coord;

    @SerializedName("main")
    private final Main main;

    @SerializedName("sys")
    private final Sys sys;

    @SerializedName("weather")
    private final Weather weather;

    public ListOfCities(long id, String name, Coord coord, Main main, Sys sys, Weather weather) {
        this.id = id;
        this.name = name;
        this.coord = coord;
        this.main = main;
        this.sys = sys;
        this.weather = weather;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coord getCoord() {
        return coord;
    }

    public Main getMain() {
        return main;
    }

    public Sys getSys() {
        return sys;
    }

    public Weather getWeather() {
        return weather;
    }
}
