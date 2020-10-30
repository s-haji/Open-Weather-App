package com.suadahaji.weatherapp.ui.settings

import androidx.lifecycle.ViewModel
import com.suadahaji.weatherapp.data.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {

    private var viewModelJob = Job()

    fun deleteAllCities() = CoroutineScope(viewModelJob + Dispatchers.Main).launch {
        mainRepository.deleteAllCities(mainRepository.fetchAllCities())
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}