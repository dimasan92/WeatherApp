package ru.geekbrains.weatherapp.view.screens.welcome;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.presenter.welcome.IWelcomePresenter;

class WelcomeAdapter extends RecyclerView.Adapter<WelcomeAdapter.CityHolder> {

    private List<String> mCities;
    private IWelcomePresenter mPresenter;

    WelcomeAdapter(List<String> cities, IWelcomePresenter presenter) {
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
        holder.bind(mCities.get(position));
    }

    @Override
    public int getItemCount() {
        return mCities.size();
    }

    public void setCities(List<String> cities) {
        mCities = cities;
    }

    class CityHolder extends RecyclerView.ViewHolder {

        private TextView mCityItem;

        CityHolder(View CityItem) {
            super(CityItem);

            itemView.setOnClickListener(v -> {
                String city = ((TextView) v.findViewById(R.id.item_city_name)).getText().toString();
                mPresenter.onListItemClick(city);
            });
            mCityItem = itemView.findViewById(R.id.item_city_name);
        }

        void bind(String city) {
            mCityItem.setText(city);
        }
    }
}
