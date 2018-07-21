package ru.geekbrains.weatherapp.screens;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.application.App;
import ru.geekbrains.weatherapp.di.screen.ScreenComponent;
import ru.geekbrains.weatherapp.screens.favorites.implementations.FavoritesView;
import ru.geekbrains.weatherapp.screens.main.implementations.MainView;
import ru.geekbrains.weatherapp.screens.settings.implementations.SettingsView;

public class AppActivity extends AppCompatActivity {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView mBottomNavigationView;
    @BindView(R.id.appbar_background)
    ImageView mAppBarBackground;

    private BottomNavigationView.OnNavigationItemSelectedListener mListener =
            item -> {
                switch (item.getItemId()) {
                    case R.id.navigation_favorites:
                        initFavouritesScreen();
                        return true;
                    case R.id.navigation_main:
                        initMainScreen();
                        return true;
                    case R.id.navigation_settings:
                        initSettingsScreen();
                        return true;
                }
                return false;
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            mBottomNavigationView.setSelectedItemId(R.id.navigation_main);
        }
        mBottomNavigationView.setOnNavigationItemSelectedListener(mListener);
        initBasicScreen();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        switch (mBottomNavigationView.getSelectedItemId()) {
            case R.id.navigation_favorites:
                setFavoritesToolbar();
                break;
            case R.id.navigation_main:
                setMainToolbar();
                break;
            case R.id.navigation_settings:
                setSettingsToolbar();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBottomNavigationView.setOnNavigationItemSelectedListener(null);
    }

    private void initBasicScreen() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            initMainScreen();
        }
    }

    private void initFavouritesScreen() {
        setFavoritesToolbar();
        FavoritesView favoritesView = FavoritesView.newInstance();
        initComponent().inject(favoritesView);
        initFragment(favoritesView);
    }

    private void initMainScreen() {
        setMainToolbar();
        MainView mainView = MainView.newInstance();
        initComponent().inject(mainView);
        initFragment(mainView);
    }

    private void initSettingsScreen() {
        setSettingsToolbar();
        SettingsView settingsView = SettingsView.newInstance();
        initComponent().inject(settingsView);
        initFragment(settingsView);
    }

    private void initFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }

    private ScreenComponent initComponent() {
        App.getApp().clearScreenComponent();
        return App.getApp().getScreenComponent();
    }

    private void setFavoritesToolbar() {
        mAppBarBackground.setImageResource(R.drawable.toolbar_favorites);
    }

    private void setMainToolbar() {
        mAppBarBackground.setImageResource(R.drawable.toolbar_main);
    }
    
    private void setSettingsToolbar() {
        mAppBarBackground.setImageResource(R.drawable.toolbar_settings);
    }
}
