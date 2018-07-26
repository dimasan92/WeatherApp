package ru.geekbrains.weatherapp.di.screen;

import android.content.Context;
import android.hardware.SensorManager;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import ru.geekbrains.weatherapp.ui.screens.settings.contracts.ISettingsModel;
import ru.geekbrains.weatherapp.ui.screens.settings.contracts.ISettingsPresenter;
import ru.geekbrains.weatherapp.ui.screens.settings.contracts.ISettingsView;
import ru.geekbrains.weatherapp.ui.screens.settings.implementations.SettingsModel;
import ru.geekbrains.weatherapp.ui.screens.settings.implementations.SettingsPresenter;

@Module
public class SettingsModule {

    private SensorManager mSensorManager;

    public SettingsModule(Context context) {
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    }

    @ScreenScope
    @Provides
    ISettingsPresenter<ISettingsView> providePresenter(ISettingsModel model) {
        CompositeDisposable disposables = new CompositeDisposable();
        return new SettingsPresenter<>(model, disposables);
    }

    @ScreenScope
    @Provides
    ISettingsModel provideModel() {
        return new SettingsModel(mSensorManager);
    }
}
