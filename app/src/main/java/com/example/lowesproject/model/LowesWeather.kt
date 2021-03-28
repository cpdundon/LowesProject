package com.example.lowesproject.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LowesWeather(
        val city: City,
        val cnt: Int,
        val cod: String,
        val list: List<WeatherList>,
        val message: Int
)