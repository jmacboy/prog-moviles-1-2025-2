package com.example.practicamapas

import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MapViewModel : ViewModel() {


    val _showMarkInfo: MutableStateFlow<String?> = MutableStateFlow(null)
    val showMarkInfo: StateFlow<String?> = _showMarkInfo.asStateFlow()

    val _markerList:MutableStateFlow<List<LatLng>> = MutableStateFlow(emptyList())
    val markerList:StateFlow<List<LatLng>> = _markerList.asStateFlow()



    fun addMarker(latLng: LatLng) {
        val currentList = _markerList.value.toMutableList()
        currentList.add(latLng)
        _markerList.value = currentList
    }



    fun showMarkInfo(markInfo: String) {
        _showMarkInfo.value = markInfo
    }

    fun dismissMarkInfo() {
        _showMarkInfo.value = null
    }
}