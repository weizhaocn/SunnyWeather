package com.john.sunnyweather.logic

import androidx.lifecycle.liveData
import com.john.sunnyweather.logic.dao.PlaceDao
import com.john.sunnyweather.logic.model.Place
import com.john.sunnyweather.logic.model.Weather
import com.john.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.lang.Exception
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext

/**
 * Created by john_ on 2020/7/9.
 * Describe:
 */
object Repository {

    fun searchPlaces(query: String) = fire(Dispatchers.IO){
        val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
        if("ok" == placeResponse.status){
            val places = placeResponse.places
            Result.success(places)
        }else{
            Result.failure<List<Place>>(RuntimeException("response status is ${placeResponse.status}"))
        }
    }

    fun refreshWeather(lng: String, lat: String) = fire(Dispatchers.IO) {
        coroutineScope {
            val deferredRealtime = async {
                SunnyWeatherNetwork.getRealtimeWeather(lng, lat)
            }
            val deferredDaily = async {
                SunnyWeatherNetwork.getDailyWeather(lng, lat)
            }
            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDaily.await()
            if(realtimeResponse.status == "ok" && dailyResponse.status == "ok"){
                val weather = Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
                Result.success(weather)
            }else{
                Result.failure<Weather>(RuntimeException("realtime response status is ${realtimeResponse.status}" +
                    "daily response status is ${dailyResponse.status}"))
            }
        }
    }

    private fun <T> fire(context: CoroutineContext, block: suspend() -> Result<T>) = liveData<Result<T>>(context) {
        var result = try{
            block()
        }catch (e: Exception){
            Result.failure<T>(e)
        }
        emit(result)
    }

    fun savePlace(place: Place) = PlaceDao.savePlace(place)

    fun getSavedPlace() = PlaceDao.getSavedPlace()

    fun isPlaceSaved() = PlaceDao.isPlcaceSaved()
}