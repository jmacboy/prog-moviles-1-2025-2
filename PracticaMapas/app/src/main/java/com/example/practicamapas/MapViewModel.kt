package com.example.practicamapas

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicamapas.remote.DestinationRepository
import com.example.practicamapas.tools.Tools
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MapViewModel : ViewModel() {


    private val _routePoints = mutableStateOf<List<LatLng>>(emptyList())
    val routePoints = _routePoints

    fun getDirection(origin: LatLng, destination: LatLng) = viewModelScope.launch {
        DestinationRepository().getDestination(origin, destination).fold(
            onSuccess =  {line ->
                val res = Tools.decodePolyline(line)
                _routePoints.value = res
            },
            onFailure = {

            }
        )
    }




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