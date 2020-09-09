package com.suadahaji.weatherapp.ui.addcity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.microsoft.appcenter.analytics.Analytics
import com.suadahaji.weatherapp.data.api.WeatherResponse
import com.suadahaji.weatherapp.data.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddCityViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {
    private val _lat = MutableLiveData<String>()
    private val _lon = MutableLiveData<String>()
    private val _units = MutableLiveData<String>()

    private var viewModelJob = Job()
    private var _weather = MutableLiveData<WeatherResponse>()
    val weather: LiveData<WeatherResponse>
        get() = _weather


    fun setQuery(lat: String?, lon: String?, units: String?) {
        _lat.value = lat
        _lon.value = lon
        _units.value = units
    }


    fun fetchCityWeather() = CoroutineScope(viewModelJob + Dispatchers.Main).launch {
        try {
            val request = mainRepository.fetchCityWeatherByLatLng(
                _lat.value!!,
                _lon.value!!,
                _units.value!!
            )
            if (request.isSuccessful) {
                _weather.value = request.body()
                if (_weather.value!!.name.isNotEmpty()) {
                    val response = request.body()
                    response?.let {
                        mainRepository.addCity(it)
                    }
                }
            } else {
                _weather.value = null
                Log.e(TAG, "fetchCityWeather: ${request.errorBody().toString()}")
            }
        } catch (e: Exception) {
            Log.e(TAG, "fetchCityWeather: ${e.message}")
            Analytics.trackEvent(e.message)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    companion object {
        private const val TAG = "CityListViewModel"
    }

}