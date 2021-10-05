package com.valhallaonlineservices.eventspass.nativeappexam.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.valhallaonlineservices.eventspass.nativeappexam.data.db.entities.UsersEntity
import io.reactivex.Completable

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UsersEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<UsersEntity>): Completable
}
