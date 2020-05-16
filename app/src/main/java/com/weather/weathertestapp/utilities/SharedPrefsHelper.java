package com.weather.weathertestapp.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.weather.weathertestapp.WeatherApp;

public class SharedPrefsHelper {

    private static final String TAG = "SharedPrefsHelper";

    private static final String SHARED_PREFS_NAME = "TestData";

    private static SharedPrefsHelper instance;

    private SharedPreferences sharedPreferences;


    public static synchronized SharedPrefsHelper getInstance() {
        if (instance == null) {
            instance = new SharedPrefsHelper();
        }
        return instance;
    }

    private SharedPrefsHelper() {
        instance = this;
        sharedPreferences = WeatherApp.getInstance().getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
    }

    public boolean delete(String key) {
        boolean result = false;
        if (sharedPreferences.contains(key)) {
            result = getEditor().remove(key).commit();
        }
        return result;
    }

    public void save(String key, Object value) {
        SharedPreferences.Editor editor = getEditor();
        if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (int) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Enum) {
            editor.putString(key, value.toString());
        } else if (value != null) {
            throw new RuntimeException("Attempting to save non-supported preference");
        }

        editor.commit();
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) sharedPreferences.getAll().get(key);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key, T defValue) {
        T returnValue = (T) sharedPreferences.getAll().get(key);
        return returnValue == null ? defValue : returnValue;
    }

    public boolean has(String key) {
        return sharedPreferences.contains(key);
    }

    private SharedPreferences.Editor getEditor() {
        return sharedPreferences.edit();
    }

}
