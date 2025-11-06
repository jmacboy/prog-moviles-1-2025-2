package com.example.practicamapas

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.flow.MutableStateFlow

class LocationUpdates(private val context: Context) {


    private val fusedLocation = LocationServices.getFusedLocationProviderClient(context)

    val currentLocation = MutableStateFlow<Location?>(null)

    private val locationCallback: LocationCallback = object : LocationCallback(){
        override fun onLocationResult(location: LocationResult) {
            location.lastLocation?.let {
                currentLocation.value = it
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun startLocationUpdates(){
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY, 10000
        )
            .setWaitForAccurateLocation(false)
            .setMinUpdateIntervalMillis(2000)
            .setMaxUpdateDelayMillis(10000)
            .setMinUpdateDistanceMeters(2f)
            .build()

        fusedLocation.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()

        )

    }



    fun stopLocationUpdates(){
        fusedLocation.removeLocationUpdates(locationCallback)
    }


}