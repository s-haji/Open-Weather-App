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
    private val weatherDao: WeatherDao,
    private val apiKey: String
) {
    suspend fun fetchCityWeatherByName(cityName: String, units: String) =
        weatherApiService.getCityWeatherByName(apiKey, cityName, units)

    suspend fun fetchCityWeatherByLatLng(lat: String, lon: String, units: String) =
        weatherApiService.getCityWeatherByLatLng(apiKey, lat, lon, units)

    suspend fun fetchAllCities(): List<CityModel> = weatherDao.getAllCities()

    suspend fun addCity(weatherResponse: WeatherResponse) {
        val city = CityModel(
            weatherResponse.dt,
            weatherResponse.id,
            weatherResponse.name,
            weatherResponse.cod,
            weatherResponse.weather[0].description,
            weatherResponse.weather[0].icon,
            weatherResponse.main.temp,
            weatherResponse.sys.country,
            weatherResponse.sys.sunrise,
            weatherResponse.sys.sunset
        )
        weatherDao.bookmarkCity(city)
    }

    suspend fun fetchCityForecast(cityId: Int, units: String) =
        weatherApiService.getCityForecast(apiKey, cityId, units)
}