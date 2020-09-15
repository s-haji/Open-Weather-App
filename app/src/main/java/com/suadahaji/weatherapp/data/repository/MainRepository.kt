package com.suadahaji.weatherapp.data.repository

import com.suadahaji.weatherapp.data.api.WeatherApiService
import com.suadahaji.weatherapp.data.database.WeatherDao
import com.suadahaji.weatherapp.data.models.CityModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val weatherApiService: WeatherApiService,
    private val weatherDao: WeatherDao,
    private val apiKey: String
) {
    suspend fun fetchCityWeatherByName(cityName: String, units: String) =
        weatherApiService.getCityWeatherByName(apiKey, cityName, units)

    suspend fun fetchCityWeatherByLatLng(lat: String, lon: String, units: String) =
        weatherApiService.getCityWeatherByLatLng(apiKey, lat, lon, units)

    suspend fun fetchAllCities(): List<CityModel> = weatherDao.getAllCities()

    suspend fun addCity(city: CityModel) {
        weatherDao.bookmarkCity(city)
    }

    suspend fun deleteCity(city: CityModel) {
        weatherDao.deleteCity(city)
    }

    suspend fun fetchCityForecast(cityId: Int, units: String) =
        weatherApiService.getCityForecast(apiKey, cityId, units)

    suspend fun fetchCities(cityName: String, units: String) =
        weatherApiService.getCities(apiKey, cityName, units)
}