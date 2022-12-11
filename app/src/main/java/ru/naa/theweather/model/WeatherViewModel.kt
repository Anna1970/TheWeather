package ru.naa.theweather.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    private var itemWeather = MutableLiveData<WeatherData>()

    private val repository = WeatherRepository(application)

    fun getWeather(key: Int):MutableLiveData<WeatherData>{
        viewModelScope.launch {
            val item = repository.getWeather(key)
            itemWeather = MutableLiveData<WeatherData>(WeatherData(item.date, item.text, item.icon, item.iconPhrase, item.temperature))
        }
        return itemWeather
    }
}