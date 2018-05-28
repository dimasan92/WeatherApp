package ru.geekbrains.weatherapp.welcome;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.weather.WeatherFragment;

public class WelcomeFragment extends Fragment {

    private WelcomePresenter mPresenter;

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
        buttonTransition.setOnClickListener((v) -> {
            mPresenter.transitionClick();
        });

        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        super.onCreate(savedInstanceState);
        initPresenter();
        mPresenter.attachView(this);
    }

    private void initPresenter() {
        if (getActivity() != null) {
            FragmentManager fm = getActivity().getSupportFragmentManager();
            mPresenter = (WelcomePresenter) fm.findFragmentByTag(Constants.WELCOME_PRESENTER_TAG);
            if (mPresenter == null) {
                mPresenter = WelcomePresenter.init();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(mPresenter, Constants.WELCOME_PRESENTER_TAG);
                ft.commit();
            }
        }
    }

    public String getCityName() {
        return etCityName.getText().toString();
    }

    public boolean getPressureParam() {
        return cbPressure.isChecked();
    }

    public boolean getWindParam() {
        return cbWind.isChecked();
    }

    public boolean getHumidityParam() {
        return cbHumidity.isChecked();
    }

    public void makeToast(int stringId) {
        Toast.makeText(Objects.requireNonNull(getActivity()).getApplicationContext(),
                stringId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
