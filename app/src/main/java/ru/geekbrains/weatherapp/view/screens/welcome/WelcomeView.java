package ru.geekbrains.weatherapp.view.screens.welcome;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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

import java.util.List;
import java.util.Objects;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.presenter.welcome.IWelcomePresenter;
import ru.geekbrains.weatherapp.common.FragmentFactory;

public class WelcomeView extends Fragment implements IWelcomeView {

    private IWelcomePresenter mPresenter;

    private RecyclerView mCityRecyclerView;
    private WelcomeAdapter mAdapter;

    public static WelcomeView newInstance() {
        return new WelcomeView();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mPresenter = FragmentFactory.getWelcomePresenter(Objects.requireNonNull(getActivity()));
        mPresenter.assignModel(getActivity());
        mPresenter.attachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_welcome, container, false);

        Toolbar toolbar = view.findViewById(R.id.main_toolbar);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);

        mCityRecyclerView = view.findViewById(R.id.rw_cities);
        mCityRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        FloatingActionButton fab = view.findViewById(R.id.list_fab);
        fab.setOnClickListener(v -> mPresenter.onNewCityClick());

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
                mPresenter.onMenuItemSettingsClick();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateListView(List<String> cities) {
        if (mAdapter == null) {
            mAdapter = new WelcomeAdapter(cities, mPresenter);
            mCityRecyclerView.setAdapter(mAdapter);
        } else {
            mCityRecyclerView.setAdapter(mAdapter);
            mAdapter.setCities(cities);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
