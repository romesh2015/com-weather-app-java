package com.weather.weathertestapp.utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

import com.weather.weathertestapp.WeatherApp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {
    public static boolean getIsWifiAvailable(){
        ConnectivityManager connMgr =
                (ConnectivityManager) WeatherApp.getsAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isWifiConn = false;
        for (Network network : connMgr.getAllNetworks()) {
            NetworkInfo networkInfo = connMgr.getNetworkInfo(network);
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                isWifiConn |= networkInfo.isConnected();
            }

        }
        return isWifiConn;
    }

    public static String getCurrentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }
}
