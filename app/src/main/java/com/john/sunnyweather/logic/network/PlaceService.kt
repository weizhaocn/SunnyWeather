package com.john.sunnyweather.logic.network

import com.john.sunnyweather.SunnyWeatherApplication
import com.john.sunnyweather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by john_ on 2020/7/8.
 * Describe:
 */
interface PlaceService {

    @GET("v2/place?token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}