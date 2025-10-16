package com.example.practicaretrofit.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BaseURL = "https://jsonplaceholder.typicode.com/"

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