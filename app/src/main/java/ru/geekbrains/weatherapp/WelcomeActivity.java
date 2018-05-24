package ru.geekbrains.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    public static final String CITY_NAME = "city name";
    public static final String PRESSURE = "pressure";
    public static final String WIND = "wind";
    public static final String HUMIDITY = "humidity";

    private EditText etCityName;
    private CheckBox cbPressure;
    private CheckBox cbWind;
    private CheckBox cbHumidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        etCityName = findViewById(R.id.et_city_name);
        cbPressure = findViewById(R.id.checkbox_pressure);
        cbWind = findViewById(R.id.checkbox_wind);
        cbHumidity = findViewById(R.id.checkbox_humidity);
    }

    public void onClickTransit(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        if(etCityName.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(), "Введите название города!",
                    Toast.LENGTH_LONG).show();
            return;
        }
        intent.putExtra(CITY_NAME, etCityName.getText().toString());
        intent.putExtra(PRESSURE, cbPressure.isChecked());
        intent.putExtra(WIND, cbWind.isChecked());
        intent.putExtra(HUMIDITY, cbHumidity.isChecked());
        startActivity(intent);
    }
}
