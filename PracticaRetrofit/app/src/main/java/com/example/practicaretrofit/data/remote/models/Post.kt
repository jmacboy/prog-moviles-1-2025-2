package com.example.practicaretrofit.data.remote.models

import com.google.gson.annotations.SerializedName

data class Post(
    val userId:Int?,
    val id:Int?,
    @SerializedName("title")
    val primaryTitle:String?,
    @SerializedName("body")
    val description:String?
) {
}