package ru.naa.theweather.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CityServise {

    @GET("/locations/v1/topcities/50?&language=ru-ru")
    fun getAll(@Query("apikey") apikey: String): Call<List<CityResponse>>

    @GET("GET /currentconditions/v1/{city}?&=ru-ru")
    fun getCurrentConditions(@Path("city") city:Int,@Query("apikey") apikey: String)
}