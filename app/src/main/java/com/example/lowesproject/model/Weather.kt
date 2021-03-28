package com.example.lowesproject.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Weather(
        val description: String,
        val icon: String,
        val id: Long,
        val main: String
)