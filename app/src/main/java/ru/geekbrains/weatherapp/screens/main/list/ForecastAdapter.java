package ru.geekbrains.weatherapp.screens.main.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.geekbrains.weatherapp.R;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastHolder> {
    @NonNull
    @Override
    public ForecastHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_forecast_weather, parent, false);

        return new ForecastHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
