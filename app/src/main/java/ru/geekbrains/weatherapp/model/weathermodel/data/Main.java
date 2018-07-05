package ru.geekbrains.weatherapp.model.weathermodel.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class Main {
    @SerializedName("temp")
    private final float temp;
    @SerializedName("pressure")
    private final int pressure;
    @SerializedName("humidity")
    private final int humidity;

    public Main(float temp, int pressure, int humidity) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public float getTemp() {
        return temp;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }
}
