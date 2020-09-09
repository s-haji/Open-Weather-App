package com.suadahaji.weatherapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.suadahaji.weatherapp.data.database.WeatherDao
import com.suadahaji.weatherapp.data.database.WeatherDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
object AppModule {
    @JvmStatic
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideDb(app: Application): WeatherDatabase {
        return Room
            .databaseBuilder(app, WeatherDatabase::class.java, "weather.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideRepoDao(db: WeatherDatabase): WeatherDao {
        return db.weatherDao()
    }
}