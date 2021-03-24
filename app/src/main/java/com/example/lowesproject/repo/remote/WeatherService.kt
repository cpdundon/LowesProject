package com.example.firstweatherapp.repo.remote

import com.example.lowesproject.model.LowesWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("forecast")
    suspend fun getWeather (@Query("q") location: String, @Query("units") units: String, @Query("appid") appid: String): Response<LowesWeather>
}