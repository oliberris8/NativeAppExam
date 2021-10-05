package com.valhallaonlineservices.eventspass.nativeappexam.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.valhallaonlineservices.eventspass.nativeappexam.constants.DatabaseRoomConstants

@Entity(tableName = DatabaseRoomConstants.TABLE_COMMENTS, indices = [Index(value =
    [DatabaseRoomConstants.COLUMN_COMMENTS_POST_ID, DatabaseRoomConstants.COLUMN_COMMENTS_ID], unique = true)])
data class CommentsEntity(
    @ColumnInfo(name = DatabaseRoomConstants.COLUMN_COMMENTS_POST_ID)
    val postId: Int,
    @ColumnInfo(name = DatabaseRoomConstants.COLUMN_COMMENTS_ID)
    val id: String,
    @ColumnInfo(name = DatabaseRoomConstants.COLUMN_COMMENTS_NAME)
    val name: String,
    @ColumnInfo(name = DatabaseRoomConstants.COLUMN_COMMENTS_EMAIL)
    val email: String,
    @ColumnInfo(name = DatabaseRoomConstants.COLUMN_COMMENTS_BODY)
    val body: String
) {
    @PrimaryKey(autoGenerate = true)
    var idComments: Long? = null
}
