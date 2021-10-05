package com.valhallaonlineservices.eventspass.nativeappexam.data.repositories.interfaces

import com.valhallaonlineservices.eventspass.nativeappexam.data.db.entities.UsersEntity

interface IUsersRepository {
    fun insertUser(user: UsersEntity)

    fun insertUsers(users: List<UsersEntity>)

    fun retrieveUsersViaApi()
}
