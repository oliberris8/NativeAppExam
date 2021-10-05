package com.valhallaonlineservices.eventspass.nativeappexam.data.interactors.interfaces

import com.valhallaonlineservices.eventspass.nativeappexam.data.db.entities.UsersEntity

interface IUsersInteractor {
    fun insertUser(user: UsersEntity)

    fun insertUsers(users: List<UsersEntity>)

    fun retrieveUsersViaApi()
}
