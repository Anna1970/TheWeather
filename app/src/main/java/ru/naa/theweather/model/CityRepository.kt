package ru.naa.theweather.model

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.naa.theweather.MainActivity.Companion.keyApi
import ru.naa.theweather.api.CityResponse
import ru.naa.theweather.api.CityRestApi
import ru.naa.theweather.room.CityDao
import ru.naa.theweather.room.CityEntity

class CityRepository(private val dao:CityDao) {


    private var cities  = dao.getAll()

    fun  getAll() = cities

    suspend fun getApi(): MutableList<CityEntity> {
        var listResponse = mutableListOf<CityEntity>()
        withContext(Dispatchers.IO) {
            //API ключ тестовый, исчерпал количество обращений
            val cityService = CityRestApi.getService()
            val result= cityService.getAll(keyApi).execute()
            listResponse = converterFromResponse(result.body()) as MutableList<CityEntity>
            /*cityService.getAll().enqueue(object: Callback<List<CityResponse>>{
                override fun onResponse(
                    call: Call<List<CityResponse>>,
                    response: Response<List<CityResponse>>
                ) {
                    listResponse = converterFromResponse(response.body()) as MutableList<CityEntity>
                }
                override fun onFailure(call: Call<List<CityResponse>>, t: Throwable) {

                }

            })*/
        }
        return listResponse
    }

    fun converterFromResponse(list: List<CityResponse>?): List<CityEntity>? {
        val result = mutableListOf<CityEntity>()
        list?.forEach {
            result.add(CityEntity(it.Key, it.LocalizedName, it.Type))
        }
        return result
    }

    suspend fun getByKey(key:String): CityEntity {
        return withContext(Dispatchers.IO){
            return@withContext dao.getBykey(key)
        }
    }

    suspend fun insert(vararg cities: CityEntity){
        withContext(Dispatchers.IO){
            cities.forEach { dao.insert(it) }
        }
    }


    /*withContext(Dispatchers.IO) {
            API ключ тестовый, исчерпал количество обращений
            val cityService = CityRestApi.getService()
            val result = cityService.getAll(keyApi).execute().body()
            if (result != null) {
                return@withContext converterFromResponse(result)
                result.forEach {
                    if (it.Key != null && it.LocalizedName !=null && it.Type != null) {
                        cityDao.insert(CityEntity(it.Key, it.LocalizedName, it.Type))
                    }
                }
            }*/

}