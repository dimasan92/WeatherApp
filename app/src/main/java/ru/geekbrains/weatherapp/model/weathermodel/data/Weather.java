package ru.geekbrains.weatherapp.model.weathermodel.data;

import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("description")
    private String description;

    public Weather(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
