package com.example.lowesproject.model

data class LowesWeather(
    val city: City,
    val cnt: Int,
    val cod: String,
    val weather_list: List<WeatherList>,
    val message: Int
)