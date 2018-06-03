package ru.geekbrains.weatherapp.fragments.welcome;

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

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.common.Constants;

public class WelcomeFragment extends Fragment {

    private WelcomePresenter mPresenter;

    private EditText etCityName;
    private CheckBox cbPressure;
    private CheckBox cbWind;
    private CheckBox cbHumidity;

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
        View layout = inflater.inflate(R.layout.fragment_welcome, container, false);

        etCityName = layout.findViewById(R.id.et_enter_city_name);
        cbPressure = layout.findViewById(R.id.cb_pressure);
        cbWind = layout.findViewById(R.id.cb_wind);
        cbHumidity = layout.findViewById(R.id.cb_humidity);

        final Button buttonTransition = layout.findViewById(R.id.btn_transition);
        buttonTransition.setOnClickListener(v -> mPresenter.onTransitionClick());

        final Button buttonChooseCity = layout.findViewById(R.id.btn_choose_city);
        buttonChooseCity.setOnClickListener(v -> mPresenter.onChooseCityClick());

        return layout;
    }

    private void initPresenter() {
        if (getActivity() == null) {
            return;
        }
        FragmentManager fm = getActivity().getSupportFragmentManager();
        mPresenter = (WelcomePresenter) fm.findFragmentByTag(Constants.WELCOME_PRESENTER_TAG);

        if (mPresenter == null) {
            mPresenter = WelcomePresenter.newInstance();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(mPresenter, Constants.WELCOME_PRESENTER_TAG);
            ft.commit();
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
        Toast.makeText(getActivity(), stringId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
