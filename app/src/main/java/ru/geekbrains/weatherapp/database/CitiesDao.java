package ru.geekbrains.weatherapp.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface CitiesDao {

    @Query("SELECT * FROM citiesentity")
    List<CitiesEntity> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CitiesEntity entity);

    @Update
    void update(CitiesEntity entity);

    @Delete
    void delete(CitiesEntity entity);
}
