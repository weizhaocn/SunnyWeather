package com.john.sunnyweather

import android.app.Application
import android.content.Context

/**
 * Created by john_ on 2020/7/8.
 * Describe:
 */
class SunnyWeatherApplication: Application() {

    companion object{
        const val TOKEN = "onh6bqb9cRNEwkBE"
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}