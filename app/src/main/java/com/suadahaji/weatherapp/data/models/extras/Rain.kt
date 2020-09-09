package com.suadahaji.weatherapp.data.models.extras

import com.google.gson.annotations.SerializedName

data class Rain(
    @field:SerializedName("3h")
    val `3h`: Double
)