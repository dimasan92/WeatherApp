package ru.geekbrains.weatherapp.ui.screens.settings.implementations;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.geekbrains.weatherapp.App;
import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.ui.screens.settings.contracts.ISettingsPresenter;
import ru.geekbrains.weatherapp.ui.screens.settings.contracts.ISettingsView;

public class SettingsView extends Fragment implements ISettingsView {

    @Inject
    ISettingsPresenter<ISettingsView> mPresenter;

    @BindView(R.id.cb_wind)
    CheckBox mCbWind;
    @BindView(R.id.cb_pressure)
    CheckBox mCbPressure;
    @BindView(R.id.cb_humidity)
    CheckBox mCbHumidity;
    @BindView(R.id.sw_temp_unit)
    SwitchCompat mSwTempUnits;
    @BindView(R.id.sw_show_sensors)
    SwitchCompat mSwShowSensors;
    @BindView(R.id.cl_sensors)
    ConstraintLayout mSensorsLayout;
    @BindView(R.id.tv_val_sens_temp)
    TextView mTvSensTemp;
    @BindView(R.id.tv_val_sens_press)
    TextView mTvSensPress;
    @BindView(R.id.tv_val_sens_hum)
    TextView mTvSensHum;

    public static SettingsView newInstance() {
        return new SettingsView();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getApp().getScreenComponent().inject(this);
        mPresenter.attachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.screen_settings, container, false);
        ButterKnife.bind(this, layout);

        mSwShowSensors.setOnCheckedChangeListener((buttonView, isChecked) ->
                mPresenter.sensorsSwitchChecked());
        mPresenter.viewIsReady();

        return layout;
    }

    @Override
    public Context getAppContext() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return null;
        }
        return getActivity().getApplicationContext();
    }

    @Override
    public boolean getWindState() {
        return mCbWind.isChecked();
    }

    @Override
    public void setWindState(boolean state) {
        mCbWind.setChecked(state);
    }

    @Override
    public boolean getPressureState() {
        return mCbPressure.isChecked();
    }

    @Override
    public void setPressureState(boolean state) {
        mCbPressure.setChecked(state);
    }

    @Override
    public boolean getHumState() {
        return mCbHumidity.isChecked();
    }

    @Override
    public void setHumState(boolean state) {
        mCbHumidity.setChecked(state);
    }

    @Override
    public boolean getTempUnits() {
        return mSwTempUnits.isChecked();
    }

    @Override
    public void setTempUnits(boolean state) {
        mSwTempUnits.setChecked(state);
    }

    @Override
    public boolean getShowSensorsState() {
        return mSwShowSensors.isChecked();
    }

    @Override
    public void setShowSensorsState(boolean state) {
        mSwShowSensors.setChecked(state);
        if (state) {
            mSensorsLayout.setVisibility(View.VISIBLE);
        } else {
            mSensorsLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void setSensTempValue(String value) {
        mTvSensTemp.setText(value);
    }

    @Override
    public void setSensPressValue(String value) {
        mTvSensPress.setText(value);
    }

    @Override
    public void setSensHumValue(String value) {
        mTvSensHum.setText(value);
    }

    @Override
    public void setSensTempValue(int id) {
        mTvSensTemp.setText(id);
    }

    @Override
    public void setSensPressValue(int id) {
        mTvSensPress.setText(id);
    }

    @Override
    public void setSensHumValue(int id) {
        mTvSensHum.setText(id);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
