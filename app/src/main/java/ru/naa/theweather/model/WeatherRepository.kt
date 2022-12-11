package ru.naa.theweather.model

import android.content.Context
import androidx.lifecycle.MutableLiveData
import ru.naa.theweather.api.CityRestApi
import ru.naa.theweather.api.WeatherResponse

class WeatherRepository(context: Context) {

    suspend fun getWeather(key: Int): WeatherData {
        /*По key будет запрос к API*/
        return WeatherData("12 декабря 2022 18:53", "Пасмурно, небольшой дождь",3,"Ветер северо-восточный", "-5.1°C")
    }

}