package ru.geekbrains.weatherapp.model.weathermodel.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class Clouds {
    @SerializedName("all")
    private final int all;

    public Clouds(int all) {
        this.all = all;
    }

    public int getAll() {
        return all;
    }
}
