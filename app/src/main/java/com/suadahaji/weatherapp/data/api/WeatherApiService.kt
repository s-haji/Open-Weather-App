package com.suadahaji.weatherapp.data.api

import com.suadahaji.weatherapp.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather?appid=${BuildConfig.API_KEY}")
    suspend fun getCityWeatherByName(
        @Query("q") cityName: String,
        @Query("units") units: String
    ): Response<WeatherResponse>

    @GET("weather?appid=${BuildConfig.API_KEY}")
    suspend fun getCityWeatherByLatLng(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("units") units: String
    ): Response<WeatherResponse>

    @GET("forecast?appid=${BuildConfig.API_KEY}")
    suspend fun getCityForecast(
        @Query("id") cityId: Int,
        @Query("units") units: String
    ): Response<ForecastResponse>
}