package ru.geekbrains.weatherapp.common;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.model.IModel;
import ru.geekbrains.weatherapp.model.Model;
import ru.geekbrains.weatherapp.presenter.dialog.DialogPresenter;
import ru.geekbrains.weatherapp.presenter.dialog.IDialogPresenter;
import ru.geekbrains.weatherapp.presenter.weather.IWeatherPresenter;
import ru.geekbrains.weatherapp.presenter.weather.WeatherPresenter;
import ru.geekbrains.weatherapp.presenter.welcome.IWelcomePresenter;
import ru.geekbrains.weatherapp.presenter.welcome.WelcomePresenter;
import ru.geekbrains.weatherapp.view.screens.weather.WeatherView;
import ru.geekbrains.weatherapp.view.dialogs.newcity.NewCityDialog;
import ru.geekbrains.weatherapp.view.dialogs.sensorsindications.SensorsIndicationsDialog;
import ru.geekbrains.weatherapp.view.dialogs.settings.SettingsDialog;
import ru.geekbrains.weatherapp.view.screens.welcome.WelcomeView;

public class FragmentFactory {

    private static final String MODEL_TAG = "model_tag";

    private static final String WELCOME_PRESENTER_TAG = "welcome_presenter_tag";
    private static final String WEATHER_PRESENTER_TAG = "weather_presenter_tag";
    private static final String DIALOG_PRESENTER_TAG = "dialog_presenter_tag";

    private static final String NEW_CITY_DIALOG_TAG = "new_city_dialog_tag";
    private static final String SENSORS_INDICATIONS_DIALOG_TAG = "sensors_indications_dialog_tag";
    private static final String SETTINGS_DIALOG_TAG = "settings_dialog_tag";

    public static void initBasicView(FragmentActivity activity) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.main_fragment);

        if (fragment == null) {
            fragment = WelcomeView.newInstance();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.main_fragment, fragment);
            ft.commit();
        }
    }

    public static void initModel(FragmentActivity activity) {
        getModel(activity);
    }

    public static IModel getModel(FragmentActivity activity) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Model model = (Model) fm.findFragmentByTag(MODEL_TAG);

        if (model == null) {
            model = Model.newInstance();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(model, MODEL_TAG);
            ft.commit();
        }

        return model;
    }

    public static IWelcomePresenter getWelcomePresenter(FragmentActivity activity) {
        FragmentManager fm = activity.getSupportFragmentManager();
        WelcomePresenter wp = (WelcomePresenter) fm.findFragmentByTag(WELCOME_PRESENTER_TAG);

        if (wp == null) {
            wp = WelcomePresenter.newInstance();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(wp, WELCOME_PRESENTER_TAG);
            ft.commit();
        }

        return wp;
    }

    public static IWeatherPresenter getWeatherPresenter(FragmentActivity activity, Bundle bundle) {
        FragmentManager fm = activity.getSupportFragmentManager();
        WeatherPresenter wp = (WeatherPresenter) fm.findFragmentByTag(WEATHER_PRESENTER_TAG);

        if (wp == null) {
            wp = WeatherPresenter.newInstance(bundle);
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(wp, WEATHER_PRESENTER_TAG);
            ft.commit();
        }

        return wp;
    }

    public static IDialogPresenter getDialogPresenter(FragmentActivity activity) {
        FragmentManager fm = activity.getSupportFragmentManager();
        DialogPresenter dp = (DialogPresenter) fm.findFragmentByTag(DIALOG_PRESENTER_TAG);

        if (dp == null) {
            dp = DialogPresenter.newInstance();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(dp, DIALOG_PRESENTER_TAG);
            ft.commit();
        }

        return dp;
    }

    public static void showNewCityDialog(FragmentActivity activity) {
        FragmentManager fm = activity.getSupportFragmentManager();
        NewCityDialog dialog = NewCityDialog.newInstance();
        dialog.show(fm, NEW_CITY_DIALOG_TAG);
    }

    public static void showSensorsIndicationsDialog(FragmentActivity activity) {
        FragmentManager fm = activity.getSupportFragmentManager();
        SensorsIndicationsDialog dialog = SensorsIndicationsDialog.newInstance();
        dialog.show(fm, SENSORS_INDICATIONS_DIALOG_TAG);
    }

    public static void showSettingsDialog(FragmentActivity activity) {
        FragmentManager fm = activity.getSupportFragmentManager();
        SettingsDialog dialog = SettingsDialog.newInstance();
        dialog.show(fm, SETTINGS_DIALOG_TAG);
    }

    public static void showWeatherScreen(FragmentActivity activity, String cityName) {
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_fragment, WeatherView.newInstance(cityName));
        ft.addToBackStack(null);
        ft.commit();
    }
}