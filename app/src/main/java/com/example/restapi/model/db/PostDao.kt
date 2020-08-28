package com.example.restapi.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.restapi.model.pojo.Post

@Dao
interface PostDao {

    //Insertar un listado de post
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPost(listPost: List<Post>)

    // Insertar 1 post
    @Insert
    suspend fun insertPost(post: Post)

    // traer todos los elementos de la tabla
    @Query("SELECT * FROM post_table ORDER BY id DESC")
    fun getAllPostList() : LiveData<List<Post>>

    //Borrarlos todos
    @Query("DELETE FROM post_table")
    suspend fun deleteAllPost()

}