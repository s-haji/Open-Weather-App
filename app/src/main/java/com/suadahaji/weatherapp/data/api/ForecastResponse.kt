package com.suadahaji.weatherapp.data.api

import com.google.gson.annotations.SerializedName
import com.suadahaji.weatherapp.data.models.City
import com.suadahaji.weatherapp.data.models.Forecast

data class ForecastResponse (
    @field:SerializedName("cod")
    val cod : String,
    @field:SerializedName("cnt")
    val cnt : Int,
    @field:SerializedName("list")
    val forecasts : List<Forecast>,
    @field:SerializedName("city")
    val city : City
)