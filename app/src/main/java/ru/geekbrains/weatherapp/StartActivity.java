package ru.geekbrains.weatherapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.common.Model;
import ru.geekbrains.weatherapp.fragments.choosecity.ChooseCityPresenter;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        FragmentManager fm = getSupportFragmentManager();
        Model model = (Model) fm.findFragmentByTag(Constants.MODEL_TAG);

        if (model == null) {
            model = Model.newInstance();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(model, Constants.MODEL_TAG);
            ft.commit();
        }
    }
}
