package com.valhallaonlineservices.eventspass.nativeappexam.data.interactors.implementations

import com.valhallaonlineservices.eventspass.nativeappexam.data.db.entities.UsersEntity
import com.valhallaonlineservices.eventspass.nativeappexam.data.interactors.interfaces.IUsersInteractor
import com.valhallaonlineservices.eventspass.nativeappexam.data.repositories.interfaces.IUsersRepository

class UsersInteractor(private val iUsersRepository: IUsersRepository) : IUsersInteractor {
    override fun insertUser(user: UsersEntity) {
        iUsersRepository.insertUser(user)
    }

    override fun insertUsers(users: List<UsersEntity>) {
        iUsersRepository.insertUsers(users)
    }

    override fun retrieveUsersViaApi() {
        iUsersRepository.retrieveUsersViaApi()
    }
}
