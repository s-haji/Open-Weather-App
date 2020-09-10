package com.suadahaji.weatherapp.data.models.extras

import com.google.gson.annotations.SerializedName

data class WeatherSys(
    @field:SerializedName("country")
    val country: String,
    @field:SerializedName("sunrise")
    val sunrise: Int,
    @field:SerializedName("sunset")
    val sunset: Int
)