package com.weather.weathertestapp.network;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class ApiErrorsResponseHandling {
    @SerializedName("cod")
    @Expose
    private int errorCode;

    @SerializedName("message")
    @Expose
    private String message;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
