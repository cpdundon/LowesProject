package com.example.lowesproject.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sys(
        val pod: String
)