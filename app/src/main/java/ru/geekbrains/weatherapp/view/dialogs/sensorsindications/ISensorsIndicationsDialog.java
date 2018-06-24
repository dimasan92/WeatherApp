package ru.geekbrains.weatherapp.view.dialogs.sensorsindications;

import ru.geekbrains.weatherapp.view.IView;

public interface ISensorsIndicationsDialog extends IView {

    void setTemperature(String temperature);

    void setPressure(String pressure);

    void setHumidity(String humidity);
}
