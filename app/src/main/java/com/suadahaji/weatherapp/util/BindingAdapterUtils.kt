package com.suadahaji.weatherapp.util

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.suadahaji.weatherapp.data.api.ForecastResponse
import java.text.DecimalFormat
import java.text.SimpleDateFormat
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
    view.text = df.format(rain)
}

@SuppressLint("SimpleDateFormat")
@BindingAdapter("calculateTime")
fun calculateTime(view: TextView, forecast: ForecastResponse?) {

    if (forecast != null) {
        val sunrise = SimpleDateFormat("EEE, MMM dd h:mm a")
        val date = Date()
        val utc = date.time + (date.timezoneOffset * 60000)
        val atlanta = utc + (1000 * forecast.city.timezone)
        val newDate = Date(atlanta)

        view.text = sunrise.format(newDate)
    }
}