package com.example.restapi.remote

import com.example.restapi.pojo.Post
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface Api {
    @GET("/posts")
    fun getAllPosts(): Call<ArrayList<Post>>

    @DELETE("/posts/{postId}")
    fun deletePost(@Path("postId") postId: Int?) : Call<Void>

    @Headers("Content-Type: application/json; charset=UTF-8")
    @POST("/posts")
    fun createNewPost(@Body post: Post): Call<Post>


}