package com.weather.weathertestapp.db;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "WeatherDetail")
public class WeatherDetail {
    @PrimaryKey(autoGenerate = false)
    public Long id;

    @ColumnInfo(name = "location")
    public String location;

    @ColumnInfo(name = "country")
    public String country;

    @ColumnInfo(name = "weather_type")
    public String weatherType;

    @ColumnInfo(name = "temp")
    public float temp;

    @ColumnInfo(name = "max_temp")
    public float maxTemp;

    @ColumnInfo(name = "min_temp")
    public float minTemp;

    @ColumnInfo(name = "feel_like")
    public float feelLike;

    @ColumnInfo(name = "pressure")
    public int pressure;

    @ColumnInfo(name = "humidity")
    public int humidity;

    @ColumnInfo(name = "visibility")
    public String visibility;

    @ColumnInfo(name = "time_stamp")
    public String timeStamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(float maxTemp) {
        this.maxTemp = maxTemp;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(float minTemp) {
        this.minTemp = minTemp;
    }

    public float getFeelLike() {
        return feelLike;
    }

    public void setFeelLike(float feelLike) {
        this.feelLike = feelLike;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}