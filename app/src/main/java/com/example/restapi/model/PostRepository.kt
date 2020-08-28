package com.example.restapi.model

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.restapi.model.db.PostRoomDatabase
import com.example.restapi.model.pojo.Post
import com.example.restapi.model.remote.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostRepository(context: Context) {

    private val tag = "PostRepository"
    //esto viene  de la Base de datos
    private val db: PostRoomDatabase = PostRoomDatabase.getDatabase(context)
    private val postList = db.postDao().getAllPostList()


    fun passLiveDataToViewModel() : LiveData<List<Post>> {
        return postList
    }

    // esto hace la llamada a retrofit
    fun fetchDataFromServer() {
        val service = RetrofitClient.retrofitInstance()
        val call = service.getAllPosts()

        call.enqueue(object :  Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                Log.d(tag, response.body().toString())
                CoroutineScope(Dispatchers.IO).launch {
                    response.body()?.let { db.postDao().insertAllPost(it) }
                }
            }
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.d(tag, t.message.toString())
            }
        })

    }

}