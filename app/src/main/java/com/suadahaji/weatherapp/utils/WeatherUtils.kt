package com.suadahaji.weatherapp.utils

import android.util.Log
import com.suadahaji.weatherapp.R

object WeatherUtils {
    private val TAG = WeatherUtils::class.java.simpleName
    fun getIconForWeatherCondition(weatherIcon: String): Int {
        if (weatherIcon.contains("01")) {
            return R.drawable.weather_01
        } else if (weatherIcon.contains("02")) {
            return R.drawable.weather_02
        } else if (weatherIcon.contains("03")) {
            return R.drawable.weather_03
        } else if (weatherIcon.contains("04")) {
            return R.drawable.weather_04
        } else if (weatherIcon.contains("09")) {
            return R.drawable.weather_09
        } else if (weatherIcon.contains("10")) {
            return R.drawable.weather_10
        } else if (weatherIcon.contains("11")) {
            return R.drawable.weather_11
        } else if (weatherIcon.contains("13")) {
            return R.drawable.weather_13
        } else if (weatherIcon.contains("50")) {
            return R.drawable.weather_50
        }
        Log.d(TAG, "Unknown Weather Icon: $weatherIcon")
        return R.drawable.weather_03
    }
}
