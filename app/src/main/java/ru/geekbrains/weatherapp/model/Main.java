package ru.geekbrains.weatherapp.model;

import com.google.gson.annotations.SerializedName;

public final class Main {

    @SerializedName("temp")
    private final float temperature;
    @SerializedName("pressure")
    private final int pressure;
    @SerializedName("humidity")
    private final int humidity;

    Main(float temperature, int pressure, int humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public float getTemperature() {
        return temperature;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }
}
