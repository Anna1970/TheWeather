package ru.naa.theweather.model

data class WeatherData(
    val key: String,
    val date: String,
    val weathertext: String,
    val icon: Int,
    val wind: String,
    val temperature: Double
)

