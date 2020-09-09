package com.suadahaji.weatherapp.data.models.extras

import com.google.gson.annotations.SerializedName
import com.suadahaji.weatherapp.data.models.Wind

data class Forecast(
    @field:SerializedName("clouds")
    val clouds: Clouds,
    @field:SerializedName("dt")
    val dt: Int,
    @field:SerializedName("dt_txt")
    val dt_txt: String,
    @field:SerializedName("main")
    val main: Main,
    @field:SerializedName("rain")
    val rain: Rain,
    @field:SerializedName("weather")
    val weather: List<Weather>,
    @field:SerializedName("wind")
    val wind: Wind
)