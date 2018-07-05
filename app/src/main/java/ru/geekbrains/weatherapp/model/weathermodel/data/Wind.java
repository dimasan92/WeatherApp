package ru.geekbrains.weatherapp.model.weathermodel.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class Wind {
    @SerializedName("speed")
    private final float speed;

    public Wind(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }
}
