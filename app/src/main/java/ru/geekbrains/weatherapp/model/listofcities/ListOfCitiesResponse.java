package ru.geekbrains.weatherapp.model.listofcities;


import com.google.gson.annotations.SerializedName;

public final class ListOfCitiesResponse {

    @SerializedName("list")
    private final ListOfCities[] list;

    public ListOfCitiesResponse(ListOfCities[] list) {
        this.list = list;
    }

    public ListOfCities[] getList() {
        return list;
    }
}
