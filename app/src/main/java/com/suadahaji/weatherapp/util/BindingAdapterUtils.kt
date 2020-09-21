package com.suadahaji.weatherapp.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.suadahaji.weatherapp.R

@BindingAdapter("checkVisibility")
fun View.checkVisibility(it: Boolean) {
    visibility = if (it) View.VISIBLE else View.GONE
}

@BindingAdapter("capitalizeSentence")
fun TextView.capitalizeSentence(description: String) {
    var retStr = description
    try { // We can face index out of bound exception if the string is null
        retStr = description.substring(0, 1).toUpperCase() + description.substring(1)
    } catch (e: Exception) {
    }
    text = retStr
}

@BindingAdapter("setImage")
fun ImageView.setImage(weatherIcon: String) {
    if (weatherIcon.contains("01")) {
        setImageResource(R.drawable.weather_01)
    } else if (weatherIcon.contains("02")) {
        setImageResource(R.drawable.weather_02)
    } else if (weatherIcon.contains("03")) {
        setImageResource(R.drawable.weather_03)
    } else if (weatherIcon.contains("04")) {
        setImageResource(R.drawable.weather_04)
    } else if (weatherIcon.contains("09")) {
        setImageResource(R.drawable.weather_09)
    } else if (weatherIcon.contains("10")) {
        setImageResource(R.drawable.weather_10)
    } else if (weatherIcon.contains("11")) {
        setImageResource(R.drawable.weather_11)
    } else if (weatherIcon.contains("13")) {
        setImageResource(R.drawable.weather_13)
    } else if (weatherIcon.contains("50")) {
        setImageResource(R.drawable.weather_50)
    } else {
        setImageResource(R.drawable.weather_03)
    }
}