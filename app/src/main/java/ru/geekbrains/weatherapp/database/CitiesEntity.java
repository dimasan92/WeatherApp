package ru.geekbrains.weatherapp.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class CitiesEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "city_id")
    private int cityId;

    @ColumnInfo(name = "name")
    private String name;

    public CitiesEntity(String name) {
        this.name = name;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
