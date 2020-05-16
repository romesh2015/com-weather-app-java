package com.weather.weathertestapp.db;
import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {WeatherDetail.class}, version = 1, exportSchema = false)
public abstract class WeatherDB extends RoomDatabase {

    public abstract ReportDetailDAO reportDetailDAO();

}
