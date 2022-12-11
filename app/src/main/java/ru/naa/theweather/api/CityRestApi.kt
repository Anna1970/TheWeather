package ru.naa.theweather.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CityRestApi {

    companion object{

        private val retrofit = Retrofit.Builder()
            .baseUrl("https://dataservice.accuweather.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun getService():CityServise{
            return retrofit.create(CityServise::class.java)
        }
    }
}