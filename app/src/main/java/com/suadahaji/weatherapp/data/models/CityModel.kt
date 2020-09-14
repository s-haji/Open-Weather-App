package com.suadahaji.weatherapp.data.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "cities", primaryKeys = ["id"])
data class CityModel(
    val dateAdded: Date,
    @field:SerializedName("dt")
    val dt: Long,
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("cod")
    val main: String,
    @field:SerializedName("description")
    val description: String,
    @field:SerializedName("icon")
    val icon: String,
    @field:SerializedName("temp")
    val temp: Double,
    @field:SerializedName("country")
    val country: String,
    @field:SerializedName("dt")
    val sunrise: Int,
    @field:SerializedName("dt")
    val sunset: Int
)