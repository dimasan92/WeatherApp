package ru.geekbrains.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.geekbrains.weatherapp.common.FragmentFactory;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        FragmentFactory.initModel(this);
        FragmentFactory.initBasicView(this);
    }
}
