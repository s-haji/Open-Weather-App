package com.suadahaji.weatherapp.ui.citylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suadahaji.weatherapp.data.api.WeatherResponse
import com.suadahaji.weatherapp.data.repository.MainRepository
import com.suadahaji.weatherapp.utils.NetworkState
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
    private var _weather = MutableLiveData<WeatherResponse>()
    val weather: LiveData<WeatherResponse>
        get() = _weather

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
            if (request.isSuccessful) {
                _weather.value = request.body()
                _status.value = NetworkState.SUCCESS
            } else {
                _status.value = NetworkState.error(request.errorBody().toString())
                _weather.value = null
            }
        } catch (e: Exception) {
            _status.value = NetworkState.error(e.message ?: "Unknown error")
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}