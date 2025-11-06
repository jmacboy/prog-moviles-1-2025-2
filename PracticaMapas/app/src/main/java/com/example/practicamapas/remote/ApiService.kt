package com.example.practicamapas.remote

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    //https://maps.googleapis.com/maps/api/directions/json
//?destination=Montreal
//&origin=Toronto
//&key=YOUR_API_KEY


    @GET("maps/api/directions/json")
    suspend fun getDirection(
        @Query("destination") destination: String,
        @Query("origin") origin: String,
        @Query("key") key: String
    ): Response<DirectionsResponse>
}