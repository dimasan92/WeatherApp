package ru.geekbrains.weatherapp.screens.favorites.implementations;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;
import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.application.App;
import ru.geekbrains.weatherapp.screens.base.IToolbarSetter;
import ru.geekbrains.weatherapp.screens.favorites.contracts.IFavoritesPresenter;
import ru.geekbrains.weatherapp.screens.favorites.contracts.IFavoritesView;
import ru.geekbrains.weatherapp.screens.favorites.list.CitiesAdapter;

public class FavoritesView extends Fragment implements IFavoritesView, IToolbarSetter {

    @Inject
    IFavoritesPresenter<IFavoritesView> mPresenter;
    private Consumer<List<String>> mToolbarSetter;

    @BindView(R.id.rw_cities)
    RecyclerView mCityRecyclerView;
    @BindView(R.id.tv_empty_cities_list)
    TextView mEmptyList;

    private CitiesAdapter mCitiesAdapter;

    public static FavoritesView newInstance() {
        return new FavoritesView();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getApp().getScreenComponent().inject(this);
        mPresenter.getDataForToolBar(mToolbarSetter);
        mPresenter.attachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.screen_favorites, container, false);
        ButterKnife.bind(this, layout);

        initList();
        showListLayout();

//        mPresenter.viewIsReady();

        return layout;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    private void initList() {
        mCityRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        mCitiesAdapter = new CitiesAdapter();
        mCityRecyclerView.setAdapter(mCitiesAdapter);
    }

    private void showListLayout() {
        if (mCitiesAdapter.getItemCount() == 0) {
            mCityRecyclerView.setVisibility(View.GONE);
            mEmptyList.setVisibility(View.VISIBLE);
        } else {
            mCityRecyclerView.setVisibility(View.VISIBLE);
            mEmptyList.setVisibility(View.GONE);
        }
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.screen_favorites, menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.item_settings:
//                mPresenter.onMenuItemSettingsClick();
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    public void updateListView(List<String> cities) {
//        if (mCitiesAdapter == null) {
//            mCitiesAdapter = new CitiesAdapter(cities, mPresenter);
//            mCityRecyclerView.setAdapter(mCitiesAdapter);
//        } else {
//            mCityRecyclerView.setAdapter(mCitiesAdapter);
//            mCitiesAdapter.setCities(cities);
//            mCitiesAdapter.notifyDataSetChanged();
//        }
//    }

    @Override
    public void getDataForToolbar(Consumer<List<String>> toolbarSetter) {
        mToolbarSetter = toolbarSetter;
    }
}
