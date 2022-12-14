package ru.naa.theweather.api

import java.util.*

data class WeatherResponse(
    val LocalObservationDateTime: Date,
    val WeatherText: String,
    val WeatherIcon: Int,
    val WindDirectionLocalized: String,
    val MetricTemperature: Double
    )


