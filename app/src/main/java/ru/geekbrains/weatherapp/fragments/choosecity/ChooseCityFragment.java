package ru.geekbrains.weatherapp.fragments.choosecity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.common.Constants;

public class ChooseCityFragment extends Fragment {

    private ChooseCityPresenter mPresenter;
    private RecyclerView mCityRecyclerView;

    public static ChooseCityFragment newInstance(Bundle bundle) {
        ChooseCityFragment fragment = new ChooseCityFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
        mPresenter.attachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_city, container, false);

        final Toolbar toolbar = view.findViewById(R.id.main_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        mCityRecyclerView = view.findViewById(R.id.rw_cities);
        mCityRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mPresenter.viewIsReady();

        return view;
    }

    private void initPresenter() {
        if (getActivity() == null) {
            return;
        }
        FragmentManager fm = getActivity().getSupportFragmentManager();
        mPresenter = (ChooseCityPresenter) fm.findFragmentByTag(Constants.CHOOSE_CITY_PRESENTER);

        if (mPresenter == null) {
            mPresenter = ChooseCityPresenter.newInstance();
            mPresenter.assignModel(getActivity());
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(mPresenter, Constants.CHOOSE_CITY_PRESENTER);
            ft.commit();
        }
    }

    public void setListView(String[] cities) {
        CitiesAdapter adapter = new CitiesAdapter(cities);
        mCityRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
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
