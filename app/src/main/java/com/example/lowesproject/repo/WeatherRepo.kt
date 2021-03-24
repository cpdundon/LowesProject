    package com.example.lowesproject.repo

import com.example.countrieskotlin.repo.remote.WeatherRetroInstance
import com.example.lowesproject.model.LowesWeather
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

