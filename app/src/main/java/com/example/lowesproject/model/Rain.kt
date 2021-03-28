package com.example.lowesproject.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Rain(
        val `3h`: Double
)