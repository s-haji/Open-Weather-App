package com.suadahaji.weatherapp.di

import com.suadahaji.weatherapp.ui.addcity.AddCityFragment
import com.suadahaji.weatherapp.ui.citydetail.CityDetailFragment
import com.suadahaji.weatherapp.ui.citylist.CityListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun bindCityListFragment(): CityListFragment

    @ContributesAndroidInjector
    abstract fun bindCityDetailFragment(): CityDetailFragment

    @ContributesAndroidInjector
    abstract fun bindAddCityFragment(): AddCityFragment
}