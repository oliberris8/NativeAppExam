package com.valhallaonlineservices.eventspass.nativeappexam.data.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.valhallaonlineservices.eventspass.nativeappexam.constants.DatabaseRoomConstants
import com.valhallaonlineservices.eventspass.nativeappexam.data.db.daos.CommentsDao
import com.valhallaonlineservices.eventspass.nativeappexam.data.db.daos.PostsDao
import com.valhallaonlineservices.eventspass.nativeappexam.data.db.daos.UsersDao
import com.valhallaonlineservices.eventspass.nativeappexam.data.db.entities.CommentsEntity
import com.valhallaonlineservices.eventspass.nativeappexam.data.db.entities.PostsEntity
import com.valhallaonlineservices.eventspass.nativeappexam.data.db.entities.UsersEntity

@Database(entities = [CommentsEntity::class, PostsEntity::class,
    UsersEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun CommentsDao(): CommentsDao

    abstract fun PostsDao(): PostsDao

    abstract fun UsersDao(): UsersDao

    companion object {
        @Volatile private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            AppDatabase::class.java, DatabaseRoomConstants.DATABASE_NAME)
            .build()
    }
}
