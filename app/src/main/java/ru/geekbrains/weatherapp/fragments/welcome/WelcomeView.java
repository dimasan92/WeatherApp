package ru.geekbrains.weatherapp.fragments.welcome;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.fragments.CommonPresenter;
import ru.geekbrains.weatherapp.fragments.CommonView;

public class WelcomeView extends CommonView {

    private RecyclerView mCityRecyclerView;

    public static WelcomeView newInstance() {
        return new WelcomeView();
    }

    @Override
    protected CommonPresenter createPresenter() {
        return WelcomePresenter.newInstance();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        initPresenter(Constants.WELCOME_PRESENTER_TAG);
        mPresenter.attachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.view_welcome, container, false);

        final Toolbar toolbar = view.findViewById(R.id.main_toolbar);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);

        mCityRecyclerView = view.findViewById(R.id.rw_cities);
        mCityRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final FloatingActionButton fab = view.findViewById(R.id.list_fab);
        fab.setOnClickListener(v -> ((WelcomePresenter) mPresenter).onAddCityClick());

        mPresenter.viewIsReady();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.view_welcome, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_settings:
                ((WelcomePresenter) mPresenter).itemSettingsSelect();
        }
        return super.onOptionsItemSelected(item);
    }

    public void updateListView(String[] cities) {
        CitiesAdapter adapter = new CitiesAdapter(cities, (WelcomePresenter) mPresenter);
        mCityRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
