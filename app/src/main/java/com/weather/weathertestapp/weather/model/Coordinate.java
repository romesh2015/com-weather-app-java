package com.weather.weathertestapp.weather.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Coordinate {
    @SerializedName("lon")
    @Expose
    private float longitude;

    @SerializedName("lat")
    @Expose
    private float latitude;

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
}
