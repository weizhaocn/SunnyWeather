package com.john.sunnyweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.john.sunnyweather.logic.Repository
import com.john.sunnyweather.logic.model.Place

/**
 * Created by john_ on 2020/7/9.
 * Describe:
 */
class PlaceViewModel: ViewModel() {

    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place>()

    val placeLiveData = Transformations.switchMap(searchLiveData){query->
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query: String){
        searchLiveData.value = query
    }

    fun savePlace(place: Place) = Repository.savePlace(place)

    fun getSavedPlace() = Repository.getSavedPlace()

    fun isPlaceSaved() = Repository.isPlaceSaved()
}