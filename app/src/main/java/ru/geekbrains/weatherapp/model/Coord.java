package ru.geekbrains.weatherapp.model;

import com.google.gson.annotations.SerializedName;

public final class Coord {

    @SerializedName("lat")
    private final double lat;
    @SerializedName("lon")
    private final double lon;

    public Coord(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
