package com.example.restapi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.restapi.model.PostRepository
import com.example.restapi.model.pojo.Post

class PostViewModel(application: Application) : AndroidViewModel(application) {

    private val repository =  PostRepository(application)
    private val postList = repository.passLiveDataToViewModel()

    fun fetchFromServer() {
        repository.fetchDataFromServer()
    }

    fun getDataFromDB(): LiveData<List<Post>> {
        return postList
    }


}