package ru.naa.theweather.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CityListViewModel(application: Application) : AndroidViewModel(application) {

    private var repository = CityRepository(application)

    private val lists = MutableLiveData<List<CityData>>()

    fun getAll(): MutableLiveData<List<CityData>> {
        viewModelScope.launch {
            repository.initCities()
            lists.value = repository.cities
        }
        return lists
    }

}