package ru.geekbrains.weatherapp.model.sensorsmodel;

public interface ISensorsData {

    String getTemperatureInd();

    String getPressureInd();

    String getHumidityInd();

    void sensorsActivate();

    void sensorsDeactivate();
}
