package com.suadahaji.weatherapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.suadahaji.weatherapp.data.models.CityModel

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bookmarkCity(cityModel: CityModel)

    @Query("SELECT * FROM cities ORDER BY dateAdded DESC")
    suspend fun getAllCities(): List<CityModel>

}