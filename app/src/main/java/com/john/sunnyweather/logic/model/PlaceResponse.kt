package com.john.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

/**
 * Created by john_ on 2020/7/8.
 * Describe:
 */
data class Location(val lng: String, val lat: String)

data class Place(val name: String,  val location: Location, @SerializedName("formatted_address") val address: String)

data class PlaceResponse(val status: String, val places: List<Place>)