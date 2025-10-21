package com.example.practicaretrofit.repository

import android.util.Log
import com.example.practicaretrofit.data.remote.models.Post
import com.example.practicaretrofit.data.remote.RetrofitInstance
import com.example.practicaretrofit.presentation.models.CommentUI
import com.example.practicaretrofit.presentation.models.PostUI
import kotlinx.coroutines.delay

class PostRepository {


    suspend fun getPosts(): Result<List<Post>> {
        return try {
            delay(1000)
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
            delay(1000)
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


    suspend fun getPostById(id:Int): Result<PostUI>{
        try {
            delay(1000)
            val response = RetrofitInstance.api.getPostById(id)
            if (response.isSuccessful){
                val data = response.body() ?: return Result.failure(Exception("No data"))

                return Result.success(PostUI(
                    userId = data.userId.toString() ?: "Unknown",
                    id = data.id.toString() ?: "Unknown",
                    primaryTitle = data.primaryTitle ?: "No Title",
                    description = data.description ?: "No Description"
                ))

            }else {
                return Result.failure(Exception("Error: ${response.code()} ${response.message()}"))
            }


        }catch (e: Exception){
            return Result.failure(e)
        }
    }


    suspend fun getCommentsById(id:Int): Result<List<CommentUI>>{
        try {
            delay(1000)
            val response = RetrofitInstance.api.getCommentsByPostId(id)
            if (response.isSuccessful){
                val data = response.body() ?: return Result.failure(Exception("No data"))
                val uiList = data.map {
                    CommentUI(
                        postId = it.postId ?: -1,
                        name = it.name ?: "No Name",
                        email = it.email ?: "No Email",
                        body = it.body ?: "No Body"
                    )
                }

                return Result.success(uiList)

            }else {
                return Result.failure(Exception("Error: ${response.code()} ${response.message()}"))
            }


        }catch (e: Exception){
            return Result.failure(e)
        }
    }
}