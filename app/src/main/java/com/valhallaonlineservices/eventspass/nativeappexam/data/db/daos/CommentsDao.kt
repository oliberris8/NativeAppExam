package com.valhallaonlineservices.eventspass.nativeappexam.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.valhallaonlineservices.eventspass.nativeappexam.data.db.entities.CommentsEntity
import io.reactivex.Completable

@Dao
interface CommentsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComment(comment: CommentsEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComments(comments: List<CommentsEntity>): Completable
}
