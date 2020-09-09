package com.suadahaji.weatherapp.data.models

import com.google.gson.annotations.SerializedName

data class Main (
    @field:SerializedName("temp")
    val temp : Double,
    @field:SerializedName("feels_like")
    val feels_like : Double,
    @field:SerializedName("temp_min")
    val temp_min : Double,
    @field:SerializedName("temp_max")
    val temp_max : Double,
    @field:SerializedName("pressure")
    val pressure : Int,
    @field:SerializedName("humidity")
    val humidity : Int
)