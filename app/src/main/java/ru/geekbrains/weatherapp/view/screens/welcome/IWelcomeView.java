package ru.geekbrains.weatherapp.view.screens.welcome;

import java.util.List;

import ru.geekbrains.weatherapp.view.IView;

public interface IWelcomeView extends IView {

    void updateListView(List<String> cities);
}
