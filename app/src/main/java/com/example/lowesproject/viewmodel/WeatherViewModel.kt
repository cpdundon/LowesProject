package com.example.lowesproject.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lowesproject.enum.StringConstants
import com.example.lowesproject.model.LowesWeather
import com.example.lowesproject.model.LowesWeatherWrapper
import com.example.lowesproject.repo.WeatherRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    companion object {
        private const val TAG = "WeatherViewModel"
    }

    private val _weather = MutableLiveData<LowesWeatherWrapper>()

    val weather: LiveData<LowesWeatherWrapper>
        get() = _weather


    init {
        fetchWeather("MumboJumbo", StringConstants.UNITS.value, StringConstants.APP_ID.value)
    }

    fun fetchWeather(location: String, units: String, appid: String) {

        viewModelScope.launch(Dispatchers.IO) {
            val weather = WeatherRepo.getWeather(location, units, appid)
            _weather.postValue(LowesWeatherWrapper(weather.code(), weather.message(), weather.body()))
        }
    }
}