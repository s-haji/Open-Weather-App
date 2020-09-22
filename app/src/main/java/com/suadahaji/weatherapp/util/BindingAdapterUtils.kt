package com.suadahaji.weatherapp.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.suadahaji.weatherapp.R
import java.text.DecimalFormat
import java.util.*


@BindingAdapter("checkVisibility")
fun View.checkVisibility(it: Boolean) {
    visibility = if (it) View.VISIBLE else View.GONE
}

@BindingAdapter("capitalizeSentence")
fun TextView.capitalizeSentence(description: String) {
    var retStr = description
    try { // We can face index out of bound exception if the string is null
        retStr = description.substring(0, 1).toUpperCase(Locale.ROOT) + description.substring(1)
    } catch (e: Exception) {
    }
    text = retStr
}

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, weatherIcon: String?) {
    val url = "https://openweathermap.org/img/wn/$weatherIcon@4x.png"
    Glide.with(view.context)
        .load(url)
        .into(view)
}

@BindingAdapter("loadRainProb")
fun loadRainProb(view: TextView, rain: Double?) {
    val df = DecimalFormat("##.##%")
    view.text =  df.format(rain)
}

@BindingAdapter("loadWeatherImage")
fun loadWeatherImage(view: ImageView, weatherIcon: String?) {
    weatherIcon?.let {
        if (weatherIcon.contains("01")) {
            view.setImageResource(R.drawable.weather_01)
        } else if (weatherIcon.contains("02")) {
            view.setImageResource(R.drawable.weather_02)
        } else if (weatherIcon.contains("03")) {
            view.setImageResource(R.drawable.weather_03)
        } else if (weatherIcon.contains("04")) {
            view.setImageResource(R.drawable.weather_04)
        } else if (weatherIcon.contains("09")) {
            view.setImageResource(R.drawable.weather_09)
        } else if (weatherIcon.contains("10")) {
            view.setImageResource(R.drawable.weather_10)
        } else if (weatherIcon.contains("11")) {
            view.setImageResource(R.drawable.weather_11)
        } else if (weatherIcon.contains("13")) {
            view.setImageResource(R.drawable.weather_13)
        } else if (weatherIcon.contains("50")) {
            view.setImageResource(R.drawable.weather_50)
        } else {
            view.setImageResource(R.drawable.weather_03)
        }
    }

}