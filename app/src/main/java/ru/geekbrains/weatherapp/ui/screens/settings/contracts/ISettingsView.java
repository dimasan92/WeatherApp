package ru.geekbrains.weatherapp.ui.screens.settings.contracts;


import android.content.Context;

import ru.geekbrains.weatherapp.ui.screens.base.IView;

public interface ISettingsView extends IView {

    Context getAppContext();

    boolean getWindState();

    void setWindState(boolean state);

    boolean getPressureState();

    void setPressureState(boolean state);

    boolean getHumState();

    void setHumState(boolean state);

    boolean getTempUnits();

    void setTempUnits(boolean state);

    boolean getShowSensorsState();

    void setShowSensorsState(boolean state);

    void setSensTempValue(String value);

    void setSensTempValue(int id);

    void setSensPressValue(String value);

    void setSensPressValue(int id);

    void setSensHumValue(String value);

    void setSensHumValue(int id);
}
