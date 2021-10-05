package com.valhallaonlineservices.eventspass.nativeappexam.singletons

import com.valhallaonlineservices.eventspass.nativeappexam.data.apis.UsersResponse

object UsersSingleton {
    var users: List<UsersResponse>
    init {
        users = ArrayList()
    }
}
