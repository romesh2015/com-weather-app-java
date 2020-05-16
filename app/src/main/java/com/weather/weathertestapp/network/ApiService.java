package com.weather.weathertestapp.network;
import com.weather.weathertestapp.weather.model.WeatherResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
public interface ApiService {
    /**
     * Weather update status Api Call
     */
    @Headers("Content-Type: application/json")
    @GET(ApiEndPoints.WEATHER_STATUS+ApiEndPoints.API_KEY)
    Observable<WeatherResponse> getWeatherReport();
}
