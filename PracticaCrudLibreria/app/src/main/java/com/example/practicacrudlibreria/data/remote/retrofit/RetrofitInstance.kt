package com.example.practicacrudlibreria.data.remote.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BaseURL = "https://apilibreria.jmacboy.com/"

    val okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer xyz")
            .build()
        chain.proceed(request)
    }.build()



    val api : ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BaseURL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }


}