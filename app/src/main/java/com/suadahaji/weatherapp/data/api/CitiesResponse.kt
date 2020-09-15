package com.suadahaji.weatherapp.data.api

import com.google.gson.annotations.SerializedName

data class CitiesResponse(
    @field:SerializedName("list")
    val list: List<WeatherResponse>
)