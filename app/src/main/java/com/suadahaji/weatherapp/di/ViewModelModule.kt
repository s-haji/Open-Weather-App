package com.suadahaji.weatherapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hajisuada.myweatherapp.di.WeatherViewModelFactory
import com.suadahaji.weatherapp.ui.addcity.AddCityViewModel
import com.suadahaji.weatherapp.ui.citydetail.CityDetailViewModel
import com.suadahaji.weatherapp.ui.citylist.CityListViewModel
import com.suadahaji.weatherapp.ui.settings.SettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CityListViewModel::class)
    abstract fun bindCityListViewModel(cityListViewModel: CityListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddCityViewModel::class)
    abstract fun bindAddCityViewModel(addCityViewModel: AddCityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CityDetailViewModel::class)
    abstract fun bindCityDetailViewModel(cityDetailViewModel: CityDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindSettingsViewModel(settingsViewModel: SettingsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: WeatherViewModelFactory): ViewModelProvider.Factory
}