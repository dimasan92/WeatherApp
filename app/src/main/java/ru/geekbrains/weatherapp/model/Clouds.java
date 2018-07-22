package ru.geekbrains.weatherapp.model;

import com.google.gson.annotations.SerializedName;

public final class Clouds {

    @SerializedName("all")
    private final int all;

    Clouds(int all) {
        this.all = all;
    }

    public int getAll() {
        return all;
    }
}
