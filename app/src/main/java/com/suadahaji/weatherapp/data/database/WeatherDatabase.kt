package com.suadahaji.weatherapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.suadahaji.weatherapp.data.models.CityModel

@Database(entities = [CityModel::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}