package com.weather.weathertestapp.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
@Dao
public interface ReportDetailDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWeatherReport(WeatherDetail... weatherDetails);

    @Query("SELECT * FROM WeatherDetail")
    WeatherDetail getWeatherReport();

    @Update
    public void updateUsers(WeatherDetail... weatherDetails);


}
