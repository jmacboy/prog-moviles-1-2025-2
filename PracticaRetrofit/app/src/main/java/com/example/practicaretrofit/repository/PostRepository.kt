package com.example.practicaretrofit.repository

import android.util.Log
import com.example.practicaretrofit.data.remote.models.Post
import com.example.practicaretrofit.data.remote.RetrofitInstance
import com.example.practicaretrofit.presentation.models.PostUI
import kotlinx.coroutines.delay

class PostRepository {


    suspend fun getPosts(): Result<List<Post>> {
        return try {
            delay(5000)
            val response = RetrofitInstance.api.getPostsResponse()
            if (response.isSuccessful){
                Result.success(response.body() ?: emptyList())
            }else {
                Result.failure(Exception("Error: ${response.code()} ${response.message()}"))
            }
        }catch (e: Exception){
            Log.e("Error", e.message.toString())
            Result.failure(e)
        }
    }

    suspend fun getPostsUI(): Result<List<PostUI>> {
        return try {
            delay(5000)
            val response = RetrofitInstance.api.getPostsResponse()
            if (response.isSuccessful){
                val list = response.body() ?: emptyList()
                val uiList = list.map {
                    PostUI(
                        userId = it.userId.toString() ?: "Unknown",
                        id = it.id.toString() ?: "Unknown",
                        primaryTitle = it.primaryTitle ?: "No Title",
                        description = it.description ?: "No Description"
                    )
                }
                Result.success(uiList)
            }else {
                Result.failure(Exception("Error: ${response.code()} ${response.message()}"))
            }
        }catch (e: Exception){
            Log.e("Error", e.message.toString())
            Result.failure(e)
        }
    }
}