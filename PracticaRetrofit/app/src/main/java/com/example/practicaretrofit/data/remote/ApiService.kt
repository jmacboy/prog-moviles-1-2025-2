package com.example.practicaretrofit.data.remote
import com.example.practicaretrofit.data.remote.models.Post
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {
//    https://jsonplaceholder.typicode.com/posts

    @GET("posts")
    suspend fun getPosts(): List<Post>


    @GET("posts")
    suspend fun getPostsResponse(): Response<List<Post>>

}