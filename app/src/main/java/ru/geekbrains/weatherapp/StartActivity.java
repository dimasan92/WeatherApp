package ru.geekbrains.weatherapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.model.DataModel;
import ru.geekbrains.weatherapp.fragments.welcome.WelcomeView;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        FragmentManager fm = getSupportFragmentManager();

        DataModel model = (DataModel) fm.findFragmentByTag(Constants.MODEL_TAG);
        if (model == null) {
            model = DataModel.newInstance();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(model, Constants.MODEL_TAG);
            ft.commit();
        }

        Fragment fragment = fm.findFragmentById(R.id.main_fragment);
        if (fragment == null) {
            fragment = WelcomeView.newInstance();
            fm.beginTransaction()
                    .add(R.id.main_fragment, fragment)
                    .commit();
        }
    }
}
