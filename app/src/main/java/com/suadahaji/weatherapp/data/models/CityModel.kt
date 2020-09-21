package com.suadahaji.weatherapp.data.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "cities", primaryKeys = ["id"])
data class CityModel(
    val dateAdded: Date,
    @field:SerializedName("dt")
    var dt: Long,
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("description")
    var description: String,
    @field:SerializedName("icon")
    var icon: String,
    @field:SerializedName("temp")
    var temp: Double,
    @field:SerializedName("country")
    val country: String,
    @field:SerializedName("sunrise")
    var sunrise: Int,
    @field:SerializedName("sunset")
    var sunset: Int
)