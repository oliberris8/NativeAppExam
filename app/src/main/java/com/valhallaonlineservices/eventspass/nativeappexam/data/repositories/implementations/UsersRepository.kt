package com.valhallaonlineservices.eventspass.nativeappexam.data.repositories.implementations

import android.util.Log
import com.valhallaonlineservices.eventspass.nativeappexam.data.apis.UsersResponse
import com.valhallaonlineservices.eventspass.nativeappexam.data.db.daos.UsersDao
import com.valhallaonlineservices.eventspass.nativeappexam.data.db.entities.CommentsEntity
import com.valhallaonlineservices.eventspass.nativeappexam.data.db.entities.UsersEntity
import com.valhallaonlineservices.eventspass.nativeappexam.data.repositories.interfaces.IUsersRepository
import com.valhallaonlineservices.eventspass.nativeappexam.enums.NotificationCenterType
import com.valhallaonlineservices.eventspass.nativeappexam.services.NotificationCenter
import com.valhallaonlineservices.eventspass.nativeappexam.services.RetrofitClient
import com.valhallaonlineservices.eventspass.nativeappexam.singletons.CommentsSingleton
import com.valhallaonlineservices.eventspass.nativeappexam.singletons.UsersSingleton
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersRepository(private val usersDao: UsersDao): IUsersRepository {
    override fun insertUser(user: UsersEntity) {
        usersDao.insertUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Log.v("[UsersRepo]", "") }, {  }
            )
    }

    override fun insertUsers(users: List<UsersEntity>) {
        usersDao.insertUsers(users)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Log.v("[UsersRepo]", "") }, {  }
            )
    }

    override fun retrieveUsersViaApi() {
        val callUsersList: Call<List<UsersResponse>> = RetrofitClient.getClientForUsers.retrieveUsers()
        callUsersList.enqueue(object: Callback<List<UsersResponse>> {
            override fun onResponse(call: Call<List<UsersResponse>>, response: Response<List<UsersResponse>>) {
                if (response.isSuccessful) {
                    UsersSingleton.users = response.body() as List<UsersResponse>
                    NotificationCenter.postNotification(
                        NotificationCenterType.ForDisplayPosts,
                        "RetrieveUsersSuccessful")
                } else {
                    NotificationCenter.postNotification(NotificationCenterType.ForDisplayPosts,
                        "RetrieveUsersFailed")
                }
            }

            override fun onFailure(call: Call<List<UsersResponse>>, t: Throwable) {
                NotificationCenter.postNotification(NotificationCenterType.ForDisplayPosts,
                    "RetrieveUsersFailed")
            }
        })
    }
}
