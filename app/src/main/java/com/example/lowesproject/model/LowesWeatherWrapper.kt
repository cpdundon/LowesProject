package com.example.lowesproject.model

data class LowesWeatherWrapper(val httpCode: Int,
                               val message: String,
                               val lowesWeather: LowesWeather?
)