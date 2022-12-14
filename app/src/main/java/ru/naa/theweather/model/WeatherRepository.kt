package ru.naa.theweather.model

import android.content.Context
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.naa.theweather.api.CityRestApi
import ru.naa.theweather.api.WeatherResponse
import ru.naa.theweather.room.CityDao
import ru.naa.theweather.room.CityEntity

class WeatherRepository() {

    fun getWeather(key: String): WeatherData? {
        /*По key будет запрос к API*/
        return  emulateApiWeather().find { it.key == key  }
    }
}

private  fun  emulateApiWeather():  List<WeatherData> {
    val result = arrayListOf<WeatherData>()
    result.add(WeatherData("288689" , "12 декабря 2022 18:53", "Пасмурно, небольшой дождь",14,"Ветер северо-восточный", -5.1))
    result.add(WeatherData("789654" , "12 декабря 2022 18:53", "Переменная облачность,без осадков",5,"Ветер восточный", 10.0))
    result.add(WeatherData("123456" , "12 декабря 2022 18:53", "Ясно, без осадков",1,"Ветер северный", -1.0))
    result.add(WeatherData("456123" , "12 декабря 2022 18:53", "Перевенная облачность, небольшой дождь",3,"Ветер южный", -5.1))
    result.add(WeatherData("789456" , "12 декабря 2022 18:53", "Пасмурно, ливень",12,"Ветер юго-восточный", -5.1))
    result.add(WeatherData("789459" , "12 декабря 2022 18:53", "Пасмурно, гроза",15,"Ветер северо-западный", -5.1))
    result.add(WeatherData("789486" , "12 декабря 2022 18:53", "Пасмурно, небольшой дождь",14,"Ветер северный", -5.1))
    result.add(WeatherData("789286" , "12 декабря 2022 18:53", "Пасмурно, небольшой дождь",14,"Ветер западный", -5.1))
    result.add(WeatherData("783486" , "12 декабря 2022 18:53", "Пасмурно, небольшой дождь",14,"Ветер северо-восточный", -5.1))
    result.add(WeatherData("749486" , "12 декабря 2022 18:53", "Пасмурно, небольшой дождь",14,"Ветер северо-восточный", 2.0))
    result.add(WeatherData("789586" , "12 декабря 2022 18:53", "Пасмурно, небольшой дождь",14,"Ветер северо-восточный", -25.1))
    result.add(WeatherData("782486" , "12 декабря 2022 18:53", "Пасмурно, небольшой дождь",14,"Ветер северо-восточный", 5.1))
    result.add(WeatherData("779486" , "12 декабря 2022 18:53", "Пасмурно, небольшой дождь",14,"Ветер северо-восточный", 3.5))
    result.add(WeatherData("729486" , "12 декабря 2022 18:53", "Пасмурно, небольшой дождь",14,"Ветер северо-восточный", 12.0))
    result.add(WeatherData("719486" , "12 декабря 2022 18:53", "Пасмурно, небольшой дождь",14,"Ветер северо-восточный", 7.0))
    return result
}