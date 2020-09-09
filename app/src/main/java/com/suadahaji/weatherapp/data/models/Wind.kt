package com.suadahaji.weatherapp.data.models

import com.google.gson.annotations.SerializedName

data class Wind (
    @field:SerializedName("speed")
    val speed : Float,
    @field:SerializedName("deg")
    val deg : Int
)