package com.example.restapi.model

import android.content.Context
import com.example.restapi.model.db.PostRoomDatabase
import com.example.restapi.model.pojo.Post
import com.example.restapi.model.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostRepository(context: Context) {

    private val tag = "PostRepository"
    //esto viene  de la Base de datos
    private val db: PostRoomDatabase = PostRoomDatabase.getDatabase(context)
    private val postList = db.postDao().getAllPostList()

    // esto hace la llamada a retrofit
    fun fetchDataFromServer() {

    }

}