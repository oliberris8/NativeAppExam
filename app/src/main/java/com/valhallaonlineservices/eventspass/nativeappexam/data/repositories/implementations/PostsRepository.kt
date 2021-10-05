package com.valhallaonlineservices.eventspass.nativeappexam.data.repositories.implementations

import android.util.Log
import com.valhallaonlineservices.eventspass.nativeappexam.data.apis.PostsResponse
import com.valhallaonlineservices.eventspass.nativeappexam.data.db.daos.PostsDao
import com.valhallaonlineservices.eventspass.nativeappexam.data.db.entities.PostsEntity
import com.valhallaonlineservices.eventspass.nativeappexam.data.repositories.interfaces.IPostsRepository
import com.valhallaonlineservices.eventspass.nativeappexam.enums.NotificationCenterType
import com.valhallaonlineservices.eventspass.nativeappexam.services.NotificationCenter
import com.valhallaonlineservices.eventspass.nativeappexam.services.RetrofitClient
import com.valhallaonlineservices.eventspass.nativeappexam.singletons.PostsSingleton
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsRepository(private val postsDao: PostsDao): IPostsRepository {
    override fun insertPost(post: PostsEntity) {
        postsDao.insertPost(post)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Log.v("[PostsRepo]", "") }, {  }
            )
    }

    override fun insertPosts(posts: List<PostsEntity>) {
        postsDao.insertPosts(posts)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Log.v("[PostsRepo]", "") }, {  }
            )
    }

    override fun retrievePostsViaApi() {
        val callPostsList: Call<List<PostsResponse>> = RetrofitClient.getClientForPosts.retrievePosts()
        callPostsList.enqueue(object: Callback<List<PostsResponse>> {
            override fun onResponse(call: Call<List<PostsResponse>>, response: Response<List<PostsResponse>>) {
                if (response.isSuccessful) {
                    PostsSingleton.posts = response.body() as List<PostsResponse>
                    NotificationCenter.postNotification(
                        NotificationCenterType.ForDisplayPosts,
                        "RetrievePostsSuccessful")
                } else {
                    NotificationCenter.postNotification(NotificationCenterType.ForDisplayPosts,
                        "RetrievePostsFailed")
                }
            }

            override fun onFailure(call: Call<List<PostsResponse>>, t: Throwable) {
                NotificationCenter.postNotification(NotificationCenterType.ForDisplayPosts,
                    "RetrievePostsFailed")
            }
        })
    }
}
