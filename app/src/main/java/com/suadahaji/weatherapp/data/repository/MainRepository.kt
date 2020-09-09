package com.suadahaji.weatherapp.data.repository

import com.suadahaji.weatherapp.data.api.WeatherApiService
import com.suadahaji.weatherapp.data.api.WeatherResponse
import com.suadahaji.weatherapp.data.database.WeatherDao
import com.suadahaji.weatherapp.data.models.CityModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val weatherApiService: WeatherApiService,
    private val weatherDao: WeatherDao
) {
    suspend fun fetchCityWeatherByName(cityName: String, units: String) =
        weatherApiService.getCityWeatherByName(cityName, units)

    suspend fun fetchCityWeatherByLatLng(lat: String, lon: String, units: String) =
        weatherApiService.getCityWeatherByLatLng(lat, lon, units)

    suspend fun fetchAllCities(): List<CityModel> = weatherDao.getAllCities()

    suspend fun addCity(weatherResponse: WeatherResponse) {
        val city = CityModel(
            weatherResponse.dt,
            weatherResponse.id,
            weatherResponse.name,
            weatherResponse.cod,
            weatherResponse.weather[0].description,
            weatherResponse.weather[0].icon,
            weatherResponse.main.temp
        )
        weatherDao.bookmarkCity(city)
    }

    suspend fun fetchCityForecast(cityId: Int, units: String) =
        weatherApiService.getCityForecast(cityId, units)
}