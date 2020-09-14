package com.suadahaji.weatherapp.ui.citylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suadahaji.weatherapp.data.models.CityModel
import com.suadahaji.weatherapp.data.repository.MainRepository
import com.suadahaji.weatherapp.util.NetworkState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class CityListViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {
    private val _cityName = MutableLiveData<String>()
    private val _units = MutableLiveData<String>()

    private var viewModelJob = Job()

    private var _cities = MutableLiveData<List<CityModel>>()
    val cities: LiveData<List<CityModel>>
        get() = _cities

    private var _status = MutableLiveData<NetworkState>()
    val status: LiveData<NetworkState>
        get() = _status

    fun setQuery(name: String?, units: String?) {
        _cityName.value = name
        _units.value = units
    }


    fun fetchCityWeather() = CoroutineScope(viewModelJob + Dispatchers.Main).launch {
        try {
            _status.value = NetworkState.LOADING
            val request = mainRepository.fetchCityWeatherByName(
                _cityName.value!!,
                _units.value!!
            )
        } catch (e: Exception) {
            _status.value = NetworkState.error(e.message ?: "Unknown error")
        }
    }

    fun fetchAllCities() = CoroutineScope(viewModelJob + Dispatchers.Main).launch {
        try {
            _status.value = NetworkState.LOADING
            val request = mainRepository.fetchAllCities()
            _cities.value = request
        } catch (e: Exception) {
            _status.value = NetworkState.error(e.message ?: "Unknown error")
        }
    }

    fun deleteCity(city: CityModel) = CoroutineScope(viewModelJob + Dispatchers.Main).launch {
        mainRepository.deleteCity(city)
        fetchAllCities()
    }

    override fun onCleared() {
        super.onCleared()
//        viewModelJob.cancel()
    }
}