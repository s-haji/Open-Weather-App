package com.suadahaji.weatherapp.di

import com.suadahaji.weatherapp.MainActivity
import com.suadahaji.weatherapp.ui.settings.SettingsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector()
    abstract fun bindSettingsActivity(): SettingsActivity
}