package ru.naa.theweather.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.launch
import ru.naa.theweather.room.CityEntity
import ru.naa.theweather.room.LocalDb

class WeatherListViewModel(application: Application) : AndroidViewModel(application) {

    private var list: LiveData<List<CityEntity>>? = null
    private var listApi = mutableListOf<CityEntity>()
    var selectedCity: MutableLiveData<CityEntity> = MutableLiveData()
    var itemWeather: MutableLiveData<WeatherData?> = MutableLiveData()


    private val dao = Room.databaseBuilder(application.applicationContext,LocalDb::class.java,"db")
        .build().cityDao()
    private val repository = CityRepository(dao)
    private val weatherRepository = WeatherRepository()


    fun getAll(): LiveData<List<CityEntity>>? {
        viewModelScope.launch {
            if (list == null) {
                list = repository.getAll()
            }
        }
        return list
    }

    fun gatApi():LiveData<List<CityEntity>?>{
        viewModelScope.launch {
            if (listApi == null) {
                listApi = repository.getApi()
            }
        }
        return MutableLiveData<List<CityEntity>>().apply{
            postValue(listApi)
        }
    }

    fun insert(vararg cityEntity: CityEntity){
        viewModelScope.launch {
            repository.insert(*cityEntity)
        }
    }

    fun getByKey(key: String){
        viewModelScope.launch {
            val city = repository.getByKey(key)
            selectedCity.value = city
        }
    }

    fun getWeather(key: String){
        viewModelScope.launch {
            val weather =  weatherRepository.getWeather(key)
            itemWeather.value = weather
        }
    }
}