package ru.geekbrains.weatherapp.ui.screens.settings.contracts;


import android.content.Context;

import io.reactivex.Observable;
import ru.geekbrains.weatherapp.ui.screens.base.IModel;

public interface ISettingsModel extends IModel {

    void saveWindState(Context context, boolean isChecked);

    boolean getWindState(Context context);

    void savePressState(Context context, boolean isChecked);

    boolean getPressState(Context context);

    void saveHumState(Context context, boolean isChecked);

    boolean getHumState(Context context);

    void saveTempUnits(Context context, boolean isChecked);

    boolean getTempUnits(Context context);

    void saveShowSensorsState(Context context, boolean isChecked);

    boolean getShowSensorsState(Context context);

    Observable<Float> getTempReadings();

    Observable<Float> getPressReadings();

    Observable<Float> getHumReadings();

    void disposeSensListeners();
}
