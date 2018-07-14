package ru.geekbrains.weatherapp.screens.welcome.implementations;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.application.App;
import ru.geekbrains.weatherapp.screens.welcome.contracts.IWelcomePresenter;
import ru.geekbrains.weatherapp.screens.welcome.contracts.IWelcomeView;
import ru.geekbrains.weatherapp.screens.welcome.di.DaggerWelcomeComponent;
import ru.geekbrains.weatherapp.screens.welcome.list.WelcomeAdapter;

public class WelcomeView extends Fragment implements IWelcomeView {

    @Inject
    IWelcomePresenter<IWelcomeView> mPresenter;

    @BindView(R.id.rw_cities)
    RecyclerView mCityRecyclerView;

    private WelcomeAdapter mAdapter;

    public static WelcomeView newInstance() {
        return new WelcomeView();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
//
//        mPresenter = FragmentFactory.getWelcomePresenter(Objects.requireNonNull(getActivity()));
//        mPresenter.assignModel(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.view_welcome, container, false);
        ButterKnife.bind(this, view);

        DaggerWelcomeComponent
                .builder()
                .appComponent(App.getAppComponent())
                .build()
                .inject(this);

        mPresenter.attachView(this);

        mCityRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        view.findViewById(R.id.list_fab)
//                .setOnClickListener(v -> mPresenter.onNewCityClick());

//        mPresenter.viewIsReady();

        return view;
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.view_welcome, menu);
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
//        if (mAdapter == null) {
//            mAdapter = new WelcomeAdapter(cities, mPresenter);
//            mCityRecyclerView.setAdapter(mAdapter);
//        } else {
//            mCityRecyclerView.setAdapter(mAdapter);
//            mAdapter.setCities(cities);
//            mAdapter.notifyDataSetChanged();
//        }
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
