package ru.geekbrains.weatherapp.screens;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.screens.welcome.implementations.WelcomeView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBasicView();
    }

    private void initBasicView() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.main_fragment);

        if (fragment == null) {
            setToolBar(R.id.main_toolbar);
            fragment = WelcomeView.newInstance();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.main_fragment, fragment);
            ft.commit();
        }
    }

    private void setToolBar(int id){
        final Toolbar toolbar = findViewById(id);
        setSupportActionBar(toolbar);
    }
}
