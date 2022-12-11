package ru.naa.theweather.model

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.naa.theweather.MainActivity.Companion.keyApi
import ru.naa.theweather.api.CityResponse
import ru.naa.theweather.api.CityRestApi
import ru.naa.theweather.room.CityEntity
import ru.naa.theweather.room.LocalDb

class CityRepository(context: Context) {

    var cities  = mutableListOf<CityData>()

    private val cityService = CityRestApi.getService()
    private val cityDao = Room.databaseBuilder(context,LocalDb::class.java,"city")
        .build().cityDao()

    suspend fun initCities() {
        withContext(Dispatchers.IO) {
            /*API ключ тестовый, исчерпал количество обращений
            val result = cityService.getAll(keyApi).execute().body()
            if (result != null) {
                cities = converterFromResponse(result)
                result.forEach {
                    if (it.Key != null && it.LocalizedName !=null && it.Type != null) {
                        cityDao.insert(CityEntity(it.Key, it.LocalizedName, it.Type))
                    }
                }
            }*/

            cities = emulateApi()
            cities.forEach {
                cityDao.insert(CityEntity(it.key, it.name, it.type))
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun emulateApi(): MutableList<CityData> {
        val result = mutableListOf<CityData>()
        result.add(CityData("288689" , "Москва", "City"))
        result.add(CityData("789654" , "Ростов", "City"))
        result.add(CityData("123456" , "Краснодар", "City"))
        result.add(CityData("456123" , "Ялта", "City"))
        result.add(CityData("789456" , "Сочи", "City"))
        result.add(CityData("789459" , "Калининград", "City"))
        result.add(CityData("789486" , "Севастополь", "City"))
        result.add(CityData("789286" , "Владивосток", "City"))
        result.add(CityData("783486" , "Красноярск", "City"))
        result.add(CityData("749486" , "Хабаровск", "City"))
        result.add(CityData("789586" , "Волгоград", "City"))
        result.add(CityData("782486" , "Омск", "City"))
        result.add(CityData("779486" , "Симферополь", "City"))
        result.add(CityData("729486" , "Керчь", "City"))
        result.add(CityData("719486" , "Ярославль", "City"))
        return result
    }

    @SuppressLint("SuspiciousIndentation")
    fun converterFromResponse(list: List<CityResponse>?): MutableList<CityData> {
        val result = mutableListOf<CityData>()
            list?.forEach {
                val city = CityData(it.Key, it.LocalizedName, it.Type)
                result.add(city)
            }

        return result
    }
}