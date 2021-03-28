package com.example.lowesproject.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Wind(
        val deg: Int,
        val speed: Double
)