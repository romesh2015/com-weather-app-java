package com.weather.weathertestapp.utilities;

import android.util.Log;

public class Tracer {

    private final static Boolean LOG_ENABLE = true;

    public static void debug(String TAG, String message) {
        if (LOG_ENABLE) {
            Log.d(Config.App_log.concat(TAG), message);
        }
    }

    public static void info(String TAG, String message) {
        if (LOG_ENABLE) {
            Log.i(Config.App_log.concat(TAG), message);
        }
    }


    public static void warning(String TAG, String message) {
        if (LOG_ENABLE) {
            Log.w(Config.App_log.concat(TAG), message);
        }
    }


}
