package com.suadahaji.weatherapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.suadahaji.weatherapp.data.models.CityModel

@Database(entities = [CityModel::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}