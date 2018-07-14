package ru.geekbrains.weatherapp.screens.view.dialogs.sensorsindications;

import ru.geekbrains.weatherapp.screens.base.IView;

public interface ISensorsIndicationsDialog extends IView {

    void setTemperature(String temperature);

    void setPressure(String pressure);

    void setHumidity(String humidity);
}
