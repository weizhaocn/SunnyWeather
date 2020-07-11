package com.john.sunnyweather.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.john.sunnyweather.SunnyWeatherApplication
import com.john.sunnyweather.logic.model.Place

/**
 * Created by john_ on 2020/7/11.
 * Describe:
 */
object PlaceDao {

    fun savePlace(place: Place){
        sharedPreferences().edit{
            putString("place", Gson().toJson(place))
        }
    }

    fun getSavedPlace(): Place{
        val placeJson = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    fun isPlcaceSaved() = sharedPreferences().contains("place")

    private fun sharedPreferences() =  SunnyWeatherApplication.context.getSharedPreferences("sunney_weather", Context.MODE_PRIVATE)
}