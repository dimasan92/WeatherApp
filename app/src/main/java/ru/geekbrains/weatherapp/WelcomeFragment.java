package ru.geekbrains.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class WelcomeFragment extends Fragment {

    public static final String CITY_NAME = "city name";
    public static final String PRESSURE_PARAM = "pressure";
    public static final String WIND_PARAM = "wind";
    public static final String HUMIDITY_PARAM = "humidity";

    private EditText etCityName;
    private CheckBox cbPressure;
    private CheckBox cbWind;
    private CheckBox cbHumidity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_welcome, container, false);
        etCityName = layout.findViewById(R.id.et_city_name);
        cbPressure = layout.findViewById(R.id.checkbox_pressure);
        cbWind = layout.findViewById(R.id.checkbox_wind);
        cbHumidity = layout.findViewById(R.id.checkbox_humidity);

        Button buttonTransition = layout.findViewById(R.id.button_transition);
        buttonTransition.setOnClickListener((v)->{
            showWeather();
        });

        return layout;
    }

    private void showWeather() {
        if(etCityName.getText().toString().trim().equals("")){
            Toast.makeText(getActivity().getApplicationContext(), R.string.empty_city_name,
                    Toast.LENGTH_LONG).show();
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(CITY_NAME, etCityName.getText().toString());
        bundle.putBoolean(PRESSURE_PARAM, cbPressure.isChecked());
        bundle.putBoolean(WIND_PARAM, cbWind.isChecked());
        bundle.putBoolean(HUMIDITY_PARAM, cbHumidity.isChecked());
        WeatherFragment fragment = WeatherFragment.createNewInstance(bundle);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.main_fragment, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}
