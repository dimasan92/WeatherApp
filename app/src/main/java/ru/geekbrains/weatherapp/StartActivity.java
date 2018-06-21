package ru.geekbrains.weatherapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.fragments.welcome.WelcomeScreen;
import ru.geekbrains.weatherapp.model.datamodel.DataModel;
import ru.geekbrains.weatherapp.model.sensormodel.SensorModel;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        addNewFragment(Constants.DATA_MODEL_TAG, DataModel.newInstance());
        addNewFragment(Constants.SENSOR_MODEL_TAG, SensorModel.newInstance());
        addNewFragment(R.id.main_fragment, WelcomeScreen.newInstance());
    }

    private void addNewFragment(String tag, Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentByTag(tag);
        if (f == null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(fragment, tag);
            ft.commit();
        }
    }

    private void addNewFragment(int id, Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(id);
        if (f == null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(id, fragment);
            ft.commit();
        }
    }
}
