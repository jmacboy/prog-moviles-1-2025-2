package com.example.practicamapas.remote

import com.google.android.gms.maps.model.LatLng

class DestinationRepository {


   suspend fun getDestination (origin: LatLng, destination: LatLng): Result<String> {
       return try {
           val res = RetrofitInstance.api.getDirection(
               origin = "${origin.latitude},${origin.longitude}", destination =  "${destination.latitude},${destination.longitude}", key = "your_api_key"
           )

           if (res.isSuccessful){
               val line = res.body()?.routes?.first()?.overview_polyline?.points ?: ""
               Result.success(line)
           }else {
               Result.failure(Exception("Something went wrong"))
           }

       }catch (e: Exception){
           Result.failure(e)
       }
    }
}