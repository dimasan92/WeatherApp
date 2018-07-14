package ru.geekbrains.weatherapp.model.currentweather;

import com.google.gson.annotations.SerializedName;

public final class Weather {

    @SerializedName("description")
    private final String description;

    Weather(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
