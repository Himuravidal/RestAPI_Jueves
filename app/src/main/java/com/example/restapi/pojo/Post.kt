package com.example.restapi.pojo

import com.google.gson.annotations.SerializedName

data class Post(@SerializedName("user_id") val userId: Long,
                val id: Int,
                val title: String,
                val body: String)