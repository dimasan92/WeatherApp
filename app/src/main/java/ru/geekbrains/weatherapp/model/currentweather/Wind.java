package ru.geekbrains.weatherapp.model.currentweather;

import com.google.gson.annotations.SerializedName;

public final class Wind {
    @SerializedName("speed")
    private final float speed;

    Wind(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }
}
