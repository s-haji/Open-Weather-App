package com.suadahaji.weatherapp.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather")
    suspend fun getCityWeatherByName(
        @Query("appid") appId: String,
        @Query("q") cityName: String,
        @Query("units") units: String
    ): Response<WeatherResponse>

    @GET("weather")
    suspend fun getCityWeatherByLatLng(
        @Query("appid") appId: String,
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("units") units: String
    ): Response<WeatherResponse>

    @GET("forecast")
    suspend fun getCityForecast(
        @Query("appid") appId: String,
        @Query("id") cityId: Int,
        @Query("units") units: String
    ): Response<ForecastResponse>

    @GET("find")
    suspend fun getCities(
        @Query("appid") appId: String,
        @Query("q") lat: String,
        @Query("units") units: String
    ): Response<CitiesResponse>
}