package com.weather.weathertestapp.jobService;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.widget.Toast;

import com.weather.weathertestapp.R;
import com.weather.weathertestapp.utilities.SharedPrefsHelper;
import com.weather.weathertestapp.utilities.Tracer;
import com.weather.weathertestapp.utilities.Utility;
import com.weather.weathertestapp.weather.presenter.WeatherPresenter;

import static com.weather.weathertestapp.utilities.AppConstants.DEFAULT_FUNCTION_CALL_REQUEST;
import static com.weather.weathertestapp.utilities.AppConstants.UPDATE_REQUEST_VAL;

public class WeatherReportJobService extends JobService {
    private static final String TAG = WeatherReportJobService.class.getSimpleName();
    private boolean jobCancelled = false;
    private WeatherPresenter weatherPresenter;
    @Override
    public boolean onStartJob(JobParameters params) {
        Tracer.info(TAG, "Job started");
        weatherPresenter= new WeatherPresenter(this);
        if(Utility.getIsWifiAvailable()){
            SharedPrefsHelper.getInstance().save(DEFAULT_FUNCTION_CALL_REQUEST,UPDATE_REQUEST_VAL);
            weatherPresenter.getWeatherInfo();
        }else{
            Toast.makeText(this,getString(R.string.connection),Toast.LENGTH_LONG).show();
        }
        jobFinished(params, false);
        Tracer.info(TAG, "Job finished");
        return true;
    }
    @Override
    public boolean onStopJob(JobParameters params) {
        jobCancelled = true;
        return true;
    }
}