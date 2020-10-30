package com.suadahaji.weatherapp.data.database

import androidx.room.*
import com.suadahaji.weatherapp.data.models.CityModel

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bookmarkCity(cityModel: CityModel)

    @Update
    suspend fun updatebookmarkedCity(cityModel: CityModel)

    @Delete
    suspend fun deleteCity(cityModel: CityModel)

    @Delete
    suspend fun deleteAllCities(cities: List<CityModel>)

    @Query("SELECT * FROM cities ORDER BY dateAdded DESC")
    suspend fun getAllCities(): List<CityModel>

    @Query("SELECT * FROM cities WHERE id = :cityId")
    suspend fun getCity(cityId: Int): CityModel

}