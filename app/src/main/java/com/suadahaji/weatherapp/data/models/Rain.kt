package com.suadahaji.weatherapp.data.models

import com.google.gson.annotations.SerializedName

data class Rain(
    @field:SerializedName("3h")
    val `3h`: Double
)