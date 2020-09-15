package com.suadahaji.weatherapp.ui.citydetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suadahaji.weatherapp.data.api.ForecastResponse
import com.suadahaji.weatherapp.data.models.CityModel
import com.suadahaji.weatherapp.data.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class CityDetailViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {
    private val cityId = MutableLiveData<Int>()
    private val _units = MutableLiveData<String>()

    private var viewModelJob = Job()
    private var _forecast = MutableLiveData<ForecastResponse>()
    val forecast: LiveData<ForecastResponse>
        get() = _forecast


    fun setQuery(cityId: Int?, units: String?) {
        this.cityId.value = cityId
        _units.value = units
    }


    fun fetchCityWeather() = CoroutineScope(viewModelJob + Dispatchers.Main).launch {
        try {
            val request = mainRepository.fetchCityForecast(
                cityId.value!!,
                _units.value!!
            )
            _forecast.value = request.body()
            if (request.isSuccessful) {
                val response = request.body()
                response?.let {
                    val city = CityModel(
                        Date(System.currentTimeMillis()),
                        it.forecasts[0].dt,
                        it.city.id,
                        it.city.name,
                        it.forecasts[0].weather[0].description,
                        it.forecasts[0].weather[0].icon,
                        it.forecasts[0].main.temp,
                        it.city.country,
                        it.city.sunrise,
                        it.city.sunset
                    )
                    mainRepository.addCity(city)
                }
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
        private const val TAG = "CityDetailViewModel"
    }
}