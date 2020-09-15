package com.suadahaji.weatherapp.data.database

import androidx.room.*
import com.suadahaji.weatherapp.data.models.CityModel

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bookmarkCity(cityModel: CityModel)

    @Delete
    suspend fun deleteCity(cityModel: CityModel)

    @Query("SELECT * FROM cities ORDER BY dateAdded DESC")
    suspend fun getAllCities(): List<CityModel>

}