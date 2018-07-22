package ru.geekbrains.weatherapp.model;

import com.google.gson.annotations.SerializedName;

public final class Sys {

    @SerializedName("country")
    private final String country;

    public Sys(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}
