package ru.geekbrains.weatherapp.screens.favorites.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.screens.favorites.contracts.IFavoritesPresenter;

public class CitiesAdapter extends RecyclerView.Adapter<CityHolder> {

    private List<String> mCities;
    private IFavoritesPresenter mPresenter;

    @NonNull
    @Override
    public CityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new CityHolder(inflater.inflate(R.layout.item_city, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CityHolder holder, int position) {
//        holder.bind(mCities.get(position));
    }

    @Override
    public int getItemCount() {
//        return mCities.size();
        return 0;
    }

    public void setCities(List<String> cities) {
        mCities = cities;
    }

//    class CityHolder extends RecyclerView.ViewHolder {
//
//        private TextView mCityItem;
//
//        CityHolder(View CityItem) {
//            super(CityItem);
//
//            itemView.setOnClickListener(v -> {
//                String city = ((TextView) v.findViewById(R.id.tv_item_city_name)).getText().toString();
////                mPresenter.onListItemClick(city);
//            });
//            mCityItem = itemView.findViewById(R.id.tv_item_city_name);
//        }
//
//        void bind(String city) {
//            mCityItem.setText(city);
//        }
//    }
}
