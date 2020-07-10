package com.john.sunnyweather.logic.model

/**
 * Created by john_ on 2020/7/9.
 * Describe:
 */
data class Weather(val realtime: RealtimeResponse.Realtime, val daily: DailyResponse.Daily)