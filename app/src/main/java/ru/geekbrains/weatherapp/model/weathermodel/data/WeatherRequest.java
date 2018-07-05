package ru.geekbrains.weatherapp.model.weathermodel.data;

import com.google.gson.annotations.SerializedName;

public final class WeatherRequest {
    @SerializedName("weather")
    private Weather[] weather;
    @SerializedName("main")
    private final Main main;
    @SerializedName("wind")
    private final Wind wind;
    @SerializedName("clouds")
    private final Clouds clouds;
    @SerializedName("name")
    private final String name;
    @SerializedName("cod")
    private final int cod;

    public WeatherRequest(Weather[] weather, Main main, Wind wind, Clouds clouds, String name, int cod) {
        this.weather = weather;
        this.main = main;
        this.wind = wind;
        this.clouds = clouds;
        this.name = name;
        this.cod = cod;
    }

    public Weather[] getWeather() {
        return weather;
    }

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public String getName() {
        return name;
    }

    public int getCod() {
        return cod;
    }
}
