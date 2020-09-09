package com.suadahaji.weatherapp.di

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.suadahaji.weatherapp.data.api.WeatherApiService
import com.suadahaji.weatherapp.utils.isNetworkAvailable
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetworkModule {
    @JvmStatic
    @Provides
    @Singleton
    fun provideOkHttpClient(
        context: Context
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(Cache(context.applicationContext.cacheDir, (10 * 1024 * 1024).toLong()))
            .addNetworkInterceptor { chain ->
                val originalResponse = chain.proceed(chain.request())
                val cache = CacheControl.Builder()
                    .maxAge(5, TimeUnit.MINUTES)
                    .build()
                originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", cache.toString())
                    .build()
            }
            .addInterceptor { chain ->
                val originalResponse = chain.proceed(chain.request())
                if (!isNetworkAvailable(context)) {
                    val cache = CacheControl.Builder()
                        .maxStale(7, TimeUnit.DAYS)
                        .build()
                    originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", cache.toString())
                        .build()
                } else {
                    originalResponse
                }
            }
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideWeatherApi(
        okHttpClient: OkHttpClient
    ): WeatherApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(WeatherApiService::class.java)
    }
}