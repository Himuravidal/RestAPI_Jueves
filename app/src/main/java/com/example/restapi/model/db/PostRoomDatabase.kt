package com.example.restapi.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.restapi.model.pojo.Post

@Database(entities = [Post::class], version = 1,exportSchema = false)
abstract class PostRoomDatabase : RoomDatabase() {

    abstract fun postDao() : PostDao

    companion object {
        @Volatile
        private var INSTANCE: PostRoomDatabase? = null

        fun getDatabase(context: Context): PostRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PostRoomDatabase::class.java,
                    "post_database")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}


