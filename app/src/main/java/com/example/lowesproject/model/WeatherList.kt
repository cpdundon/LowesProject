package com.example.lowesproject.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherList(
        val clouds: Clouds,
        val dt: Long,
        val dt_txt: String,
        val main: Main,
        val pop: Double,
        val rain: Rain?,
        val snow: Snow?,
        val sys: Sys,
        val visibility: Int,
        val weather: List<Weather>,
        val wind: Wind
)