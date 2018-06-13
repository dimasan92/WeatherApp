package ru.geekbrains.weatherapp.fragments.addcity;

import ru.geekbrains.weatherapp.R;
import ru.geekbrains.weatherapp.fragments.CommonPresenter;

public class DialogPresenter extends CommonPresenter {

    public static DialogPresenter newInstance() {
        return new DialogPresenter();
    }

    public void onConfirmAddClick() {
        if (emptyCityName()) {
            return;
        }

        if (mModel.addCity(((AddCityDialog) mView).getCityName())) {
            ((AddCityDialog) mView).makeToast(R.string.success_add_city);
        } else {
            ((AddCityDialog) mView).makeToast(R.string.fail_add_city);
        }
    }

    private boolean emptyCityName() {
        if (((AddCityDialog) mView).getCityName().trim().equals("")) {
            ((AddCityDialog) mView).makeToast(R.string.empty_city_name);
            return true;
        }
        return false;
    }
}
