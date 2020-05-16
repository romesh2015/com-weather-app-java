package com.weather.weathertestapp;
import android.app.Application;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import com.weather.weathertestapp.jobService.WeatherReportJobService;
import com.weather.weathertestapp.network.BaseRetrofitHandler;
import com.weather.weathertestapp.utilities.AppConstants;
import com.weather.weathertestapp.utilities.SharedPrefsHelper;
import com.weather.weathertestapp.utilities.Tracer;

public class WeatherApp extends Application {
    public static final String TAG=WeatherApp.class.getSimpleName();
    private static Context wContext;
    private static WeatherApp mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        wContext=this;
        mInstance = this;
        if(!SharedPrefsHelper.getInstance().has(AppConstants.IS_JOB_EXECUTED)){
            SharedPrefsHelper.getInstance().save(AppConstants.IS_JOB_EXECUTED,AppConstants.JOB_DEFAULT);
        }
        startJob();
        BaseRetrofitHandler.getInstance().setupRetrofitAndOkHttp();
    }
    public static Context getsAppContext() {
        return wContext.getApplicationContext();
    }
    public static synchronized WeatherApp getInstance() {
        return mInstance;
    }
    private void startJob(){
        if(AppConstants.JOB_DEFAULT.equalsIgnoreCase(SharedPrefsHelper.getInstance().get(AppConstants.IS_JOB_EXECUTED))){
            // This job will run automatically with in the interval of 2 hours.
            ComponentName componentName = new ComponentName(getPackageName(), WeatherReportJobService.class.getName());
            JobInfo info = new JobInfo.Builder(123, componentName)
                    .setPersisted(true)
                    .setPeriodic(120* 60 * 1000L)
                    .build();
            JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
            int resultCode = scheduler.schedule(info);
            if (resultCode == JobScheduler.RESULT_SUCCESS) {
                Tracer.info(TAG, "Job scheduled");
            } else {
                Tracer.info(TAG, "Job scheduling failed");
            }
       }

    }
}