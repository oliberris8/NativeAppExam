package com.valhallaonlineservices.eventspass.nativeappexam.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.valhallaonlineservices.eventspass.nativeappexam.constants.DatabaseRoomConstants

@Entity(tableName = DatabaseRoomConstants.TABLE_POSTS, indices = [Index(value =
[DatabaseRoomConstants.COLUMN_POSTS_USER_ID, DatabaseRoomConstants.COLUMN_POSTS_ID], unique = true)])
data class PostsEntity(
    @ColumnInfo(name = DatabaseRoomConstants.COLUMN_POSTS_USER_ID)
    val userId: Int,
    @ColumnInfo(name = DatabaseRoomConstants.COLUMN_POSTS_ID)
    val id: Int,
    @ColumnInfo(name = DatabaseRoomConstants.COLUMN_POSTS_TITLE)
    val title: String,
    @ColumnInfo(name = DatabaseRoomConstants.COLUMN_POSTS_BODY)
    val body: String
) {
    @PrimaryKey(autoGenerate = true)
    var idPosts: Long? = null
}


