package com.example.restapi.model.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "post_table")
data class Post(@SerializedName("user_id") val userId: Long,
                @PrimaryKey val id: Int,
                val title: String,
                val body: String)