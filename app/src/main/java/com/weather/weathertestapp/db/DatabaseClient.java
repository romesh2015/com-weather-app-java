package com.weather.weathertestapp.db;
import android.content.Context;
import androidx.room.Room;
public class DatabaseClient {
    private Context mCtx;
    private static DatabaseClient mInstance;

    //our app database object
    private WeatherDB weatherDB;

    public DatabaseClient(Context mCtx) {
        this.mCtx = mCtx;

        weatherDB = Room.databaseBuilder(mCtx, WeatherDB.class, "DBWeatherApp").build();
    }

    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    public WeatherDB getAppDatabase() {
        return weatherDB;
    }
}
