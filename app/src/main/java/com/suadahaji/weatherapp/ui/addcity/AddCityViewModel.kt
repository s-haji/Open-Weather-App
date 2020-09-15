package com.suadahaji.weatherapp.ui.addcity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suadahaji.weatherapp.data.api.WeatherResponse
import com.suadahaji.weatherapp.data.models.CityModel
import com.suadahaji.weatherapp.data.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
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
                        var city = CityModel(
                            Date(System.currentTimeMillis()),
                            it.dt,
                            it.id,
                            it.name,
                            it.weather[0].description,
                            it.weather[0].icon,
                            it.main.temp,
                            it.sys.country,
                            it.sys.sunrise,
                            it.sys.sunset

                        )
                        mainRepository.addCity(city)
                    }
                }
            } else {
                _weather.value = null
                Log.e(TAG, "fetchCityWeather: ${request.errorBody().toString()}")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error: ${e.message}")
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    companion object {
        private const val TAG = "AddCityViewModel"
    }
}