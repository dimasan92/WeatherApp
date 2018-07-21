package ru.geekbrains.weatherapp.screens.base;

import java.util.List;

import io.reactivex.functions.Consumer;

public interface IToolbarSetter {

    void getDataForToolbar(Consumer<List<String>> toolbarSetter);
}
