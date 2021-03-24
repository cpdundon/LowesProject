package com.example.lowesproject.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lowesproject.model.LowesWeather
import com.example.lowesproject.repo.WeatherRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    companion object {
        private const val TAG = "WeatherViewModel"
        private const val DELAY_TEN_MIN = 1000 * 60 * 10
        private const val DELAY_QUARTER_MIN = 1000 * 15
        private const val DELAY_ONE_MIN = 1000 * 60
    }

    private val _weather = MutableLiveData<LowesWeather>()

    val weather: LiveData<LowesWeather>
        get() = _weather


    init {
        fetchWeather("Mineola,NY,USA", "Imperial", "65d00499677e59496ca2f318eb68c049")
    }

    fun fetchWeather(location: String, units: String, appid: String) {

        viewModelScope.launch(Dispatchers.IO) {
            val weather = WeatherRepo.getWeather(location, units, appid)
            _weather.postValue(weather.body())
        }
    }
}