package com.suadahaji.weatherapp.ui.citydetail

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suadahaji.weatherapp.data.api.ForecastResponse
import com.suadahaji.weatherapp.data.models.CityModel
import com.suadahaji.weatherapp.data.models.extras.Forecast
import com.suadahaji.weatherapp.data.repository.MainRepository
import com.suadahaji.weatherapp.util.NetworkState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import javax.inject.Inject

class CityDetailViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {
    private val cityId = MutableLiveData<Int>()
    private val _units = MutableLiveData<String>()

    private var viewModelJob = Job()
    private var _forecast = MutableLiveData<ForecastResponse>()
    val forecast: LiveData<ForecastResponse>
        get() = _forecast

    private var _forecasts = MutableLiveData<List<Forecast>>()
    val forecasts: LiveData<List<Forecast>>
        get() = _forecasts

    private var _city = MutableLiveData<CityModel>()
    val city: LiveData<CityModel>
        get() = _city

    private var _status = MutableLiveData<NetworkState>()
    val status: LiveData<NetworkState>
        get() = _status

    fun setQuery(cityId: Int?, units: String?) {
        this.cityId.value = cityId
        _units.value = units
    }


    @SuppressLint("SimpleDateFormat")
    fun fetchCityWeather() = CoroutineScope(viewModelJob + Dispatchers.Main).launch {
        try {
            _status.value = NetworkState.LOADING
            val request = mainRepository.fetchCityForecast(
                cityId.value!!,
                _units.value!!
            )

            _forecast.value = request.body()
            if (request.isSuccessful) {
                _status.value = NetworkState.SUCCESS
                val response = request.body()
                response?.let {
                    val cityModel = mainRepository.getCity(cityId.value!!)
                    cityModel.dt = it.forecasts[0].dt
                    cityModel.description = it.forecasts[0].weather[0].description
                    cityModel.icon = it.forecasts[0].weather[0].icon
                    cityModel.temp = it.forecasts[0].main.temp
                    cityModel.sunrise = it.city.sunrise
                    cityModel.sunset = it.city.sunset

                    mainRepository.update(cityModel)
                    _city.value = cityModel

                    for (forecast in it.forecasts) {
                        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(forecast.dt_txt)
                        val sunrise = SimpleDateFormat("EEEE")
                        forecast.dt_txt = sunrise.format(date)
                    }
                    _forecasts.value = it.forecasts.distinctBy { it.dt_txt }

                }
            }
        } catch (e: Exception) {
            _status.value = NetworkState.error(e.message ?: "Unknown error")
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
