package com.suadahaji.weatherapp.data.models.extras

import com.google.gson.annotations.SerializedName

data class City(
    @field:SerializedName("country")
    val country: String,
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("sunrise")
    val sunrise: Int,
    @field:SerializedName("sunset")
    val sunset: Int,
    @field:SerializedName("timezone")
    val timezone: Int
)