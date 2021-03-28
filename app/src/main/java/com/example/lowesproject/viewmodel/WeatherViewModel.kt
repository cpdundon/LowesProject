package com.example.lowesproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lowesproject.model.LowesWeatherWrapper
import com.example.lowesproject.repo.WeatherRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val _weather = MutableLiveData<LowesWeatherWrapper>()

    val weather: LiveData<LowesWeatherWrapper>
        get() = _weather

    fun fetchWeather(location: String, units: String, appid: String) {

        viewModelScope.launch(Dispatchers.IO) {
            val weather = WeatherRepo.getWeather(location, units, appid)
            _weather.postValue(LowesWeatherWrapper(weather.code(), weather.message(), weather.body()))
        }
    }
}