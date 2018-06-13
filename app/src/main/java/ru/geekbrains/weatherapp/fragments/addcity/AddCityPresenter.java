package ru.geekbrains.weatherapp.fragments.addcity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.common.Constants;
import ru.geekbrains.weatherapp.common.Model;

public class AddCityPresenter extends Fragment{

    private Model mModel;
    private AddCityDialog mDialog;

    public static AddCityPresenter newInstance() {
        return new AddCityPresenter();
    }

    public void assignModel(FragmentActivity activity) {

        FragmentManager fm = activity.getSupportFragmentManager();
        mModel = (Model) fm.findFragmentByTag(Constants.MODEL_TAG);

        if (mModel == null) {
            mModel = Model.newInstance();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(mModel, Constants.MODEL_TAG);
            ft.commit();
        }
    }

    public void attachView(AddCityDialog dialog) {
        mDialog = dialog;
    }

    public void detachView() {
        mDialog = null;
    }

    public void onConfirmAddClick(){
        if (emptyCityName()) {
            return;
        }

        if (mModel.addCity(mDialog.getCityName())) {
            mDialog.makeToast(R.string.success_add_city);
            mDialog.dismiss();
        } else {
            mDialog.makeToast(R.string.fail_add_city);
        }
    }

    private boolean emptyCityName() {
        if (mDialog.getCityName().trim().equals("")) {
            mDialog.makeToast(R.string.empty_city_name);
            return true;
        }
        return false;
    }
}
