package com.example.restapi.model.remote

import com.example.restapi.model.pojo.Post
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface Api {

    @GET("/posts")
    fun getAllPosts(): Call<List<Post>>

    @DELETE("/posts/{postId}")
    fun deletePost(@Path("postId") postId: Int?) : Call<Void>

    @Headers("Content-Type: application/json; charset=UTF-8")
    @POST("/posts")
    fun createNewPost(@Body post: Post): Call<Post>


}