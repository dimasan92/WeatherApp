package ru.geekbrains.weatherapp.model.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {CitiesEntity.class}, version = 1, exportSchema = false)
public abstract class CitiesDatabase extends RoomDatabase{
    public abstract CitiesDao citiesDAO();
}
