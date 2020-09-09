package com.suadahaji.weatherapp.data.models.extras

import com.google.gson.annotations.SerializedName

data class Clouds (
    @field:SerializedName("all")
    val all : Int
)