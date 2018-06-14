package ru.geekbrains.weatherapp.fragments.welcome;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.geekbrains.weatherapp.R;

class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CityHolder>{

    private String[] mCities;
    private WelcomePresenter mPresenter;

    CitiesAdapter(String[] cities, WelcomePresenter presenter) {
        mCities = cities;
        mPresenter = presenter;
    }

    @NonNull
    @Override
    public CityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
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

    public void setCities(String[] cities){
        mCities = cities;
    }

    class CityHolder extends RecyclerView.ViewHolder {

        private TextView mCityName;

        CityHolder(View cityName) {
            super(cityName);

            itemView.setOnClickListener(v -> {
                String city = ((TextView) v.findViewById(R.id.item_city_name)).getText().toString();
                mPresenter.onListItemClick(city);
            });
            mCityName = itemView.findViewById(R.id.item_city_name);
        }

        void bind(String city) {
            mCityName.setText(city);
        }
    }
}
