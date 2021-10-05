package com.valhallaonlineservices.eventspass.nativeappexam.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.valhallaonlineservices.eventspass.nativeappexam.data.db.entities.PostsEntity
import io.reactivex.Completable

@Dao
interface PostsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(post: PostsEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(posts: List<PostsEntity>): Completable
}
