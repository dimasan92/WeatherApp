package ru.geekbrains.weatherapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.model.DataModel;
import ru.geekbrains.weatherapp.fragments.welcome.WelcomeScreen;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment;

        fragment = fm.findFragmentByTag(Constants.DATA_MODEL_TAG);
        if (fragment == null) {
            fragment = DataModel.newInstance();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(fragment, Constants.DATA_MODEL_TAG);
            ft.commit();
        }

        fragment = fm.findFragmentById(R.id.main_fragment);
        if (fragment == null) {
            fragment = WelcomeScreen.newInstance();
            fm.beginTransaction()
                    .add(R.id.main_fragment, fragment)
                    .commit();
        }
    }
}
