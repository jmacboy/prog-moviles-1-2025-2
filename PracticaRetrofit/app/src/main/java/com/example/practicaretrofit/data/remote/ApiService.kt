package com.example.practicaretrofit.data.remote

import com.example.practicaretrofit.data.remote.models.Comment
import com.example.practicaretrofit.data.remote.models.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
//    https://jsonplaceholder.typicode.com/posts

    @GET("posts")
    suspend fun getPosts(): List<Post>


    @GET("posts")
    suspend fun getPostsResponse(): Response<List<Post>>

    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id: Int): Response<Post>

    @GET("posts/{id}/comments")
    suspend fun getCommentsByPostId(@Path("id") id: Int): Response<List<Comment>>

}