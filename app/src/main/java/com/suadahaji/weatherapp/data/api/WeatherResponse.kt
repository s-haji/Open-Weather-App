package com.suadahaji.weatherapp.data.api

import com.google.gson.annotations.SerializedName
import com.suadahaji.weatherapp.data.models.Clouds
import com.suadahaji.weatherapp.data.models.Main
import com.suadahaji.weatherapp.data.models.Weather
import com.suadahaji.weatherapp.data.models.Wind

data class WeatherResponse (
    @field:SerializedName("weather")
    val weather : List<Weather>,
    @field:SerializedName("main")
    val main : Main,
    @field:SerializedName("wind")
    val wind : Wind,
    @field:SerializedName("clouds")
    val clouds : Clouds,
    @field:SerializedName("dt")
    val dt : Int,
    @field:SerializedName("id")
    val id : Int,
    @field:SerializedName("name")
    val name : String,
    @field:SerializedName("cod")
    val cod : String
)