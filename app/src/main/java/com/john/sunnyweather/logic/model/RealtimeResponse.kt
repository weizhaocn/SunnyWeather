package com.john.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

/**
 * Created by john_ on 2020/7/9.
 * Describe:
 */
data class RealtimeResponse(val status: String, val result: Result) {

    data class Result(val realtime: Realtime)

    data class Realtime(val skycon: String, val temperature: Float, @SerializedName("air_quality") val airQuality: AirQuality)

    data class AirQuality(val aqi: AQI)

    data class AQI(val chn: Float)
}