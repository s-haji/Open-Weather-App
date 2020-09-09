package com.suadahaji.weatherapp.data.repository

import com.suadahaji.weatherapp.data.api.WeatherApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(private val weatherApiService: WeatherApiService) {
    suspend fun fetchCityWeatherByName(cityName: String, units: String) =
        weatherApiService.getCityWeatherByName(cityName, units)

    suspend fun fetchCityWeatherByLatLng(lat: String, lon: String, units: String) =
        weatherApiService.getCityWeatherByLatLng(lat, lon, units)

    suspend fun fetchCityForecast(cityId: Int, units: String) =
        weatherApiService.getCityForecast(cityId, units)
}