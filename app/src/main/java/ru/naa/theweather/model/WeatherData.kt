package ru.naa.theweather.model

import java.util.*

data class WeatherData(
    val date: String,
    val text: String,
    val icon: Int,
    val iconPhrase: String,
    val temperature: String
)

