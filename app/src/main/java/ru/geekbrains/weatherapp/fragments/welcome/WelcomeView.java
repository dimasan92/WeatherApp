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
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.fragments.CommonPresenter;
import ru.geekbrains.weatherapp.fragments.CommonView;

public class WelcomeView extends CommonView {

    private WelcomePresenter mPresenter;
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
        initPresenter(Constants.WELCOME_PRESENTER_TAG);
        mPresenter = (WelcomePresenter) mCommonPresenter;
        mPresenter.attachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_welcome, container, false);

        final Toolbar toolbar = view.findViewById(R.id.main_toolbar);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);

        mCityRecyclerView = view.findViewById(R.id.rw_cities);
        mCityRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final FloatingActionButton fab = view.findViewById(R.id.list_fab);
        fab.setOnClickListener(v -> mPresenter.onAddCityClick());

        mPresenter.viewIsReady();

        return view;
    }

    public void updateListView(String[] cities) {
        CitiesAdapter adapter = new CitiesAdapter(cities);
        mCityRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private class CityHolder extends RecyclerView.ViewHolder {

        private TextView mCityName;

        CityHolder(View cityName) {
            super(cityName);
            itemView.setOnClickListener(v -> {
                String city = ((TextView) v.findViewById(R.id.item_city_name)).getText().toString();
                mPresenter.onItemClick(city);
            });
            mCityName = itemView.findViewById(R.id.item_city_name);
        }

        void bind(String city) {
            mCityName.setText(city);
        }
    }

    private class CitiesAdapter extends RecyclerView.Adapter<CityHolder> {

        String[] mCities;

        CitiesAdapter(String[] cities) {
            mCities = cities;
        }

        @NonNull
        @Override
        public CityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new CityHolder(inflater.inflate(R.layout.list_item_city, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull CityHolder holder, int position) {
            holder.bind(mCities[position]);
        }

        @Override
        public int getItemCount() {
            return mCities.length;
        }
    }
}
