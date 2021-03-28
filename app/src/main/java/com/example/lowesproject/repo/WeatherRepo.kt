package com.example.lowesproject.repo

import com.example.lowesproject.model.LowesWeather
import com.example.lowesproject.repo.remote.WeatherRetroInstance
import retrofit2.Response

object WeatherRepo {
    suspend fun getWeather(
            city: String,
            units: String,
            appid: String
    ): Response<LowesWeather> {
        return WeatherRetroInstance.weatherService.getWeather(city, units, appid)
    }
}

