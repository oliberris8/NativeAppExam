package com.valhallaonlineservices.eventspass.nativeappexam.data.repositories.implementations

import android.util.Log
import com.valhallaonlineservices.eventspass.nativeappexam.data.apis.CommentsResponse
import com.valhallaonlineservices.eventspass.nativeappexam.data.db.daos.CommentsDao
import com.valhallaonlineservices.eventspass.nativeappexam.data.db.entities.CommentsEntity
import com.valhallaonlineservices.eventspass.nativeappexam.data.repositories.interfaces.ICommentsRepository
import com.valhallaonlineservices.eventspass.nativeappexam.enums.NotificationCenterType
import com.valhallaonlineservices.eventspass.nativeappexam.services.NotificationCenter
import com.valhallaonlineservices.eventspass.nativeappexam.services.RetrofitClient
import com.valhallaonlineservices.eventspass.nativeappexam.singletons.CommentsSingleton
import com.valhallaonlineservices.eventspass.nativeappexam.singletons.PostsSingleton
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsRepository(private val commentsDao: CommentsDao): ICommentsRepository {
    override fun insertComment(comment: CommentsEntity) {
        commentsDao.insertComment(comment)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Log.v("[CommentsRepo]", "") }, {  }
            )
    }

    override fun insertComments(comments: List<CommentsEntity>) {
        commentsDao.insertComments(comments)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Log.v("[CommentsRepo]", "") }, {  }
            )
    }

    override fun retrieveCommentsViaApi() {
        val selectedPostId = PostsSingleton.selectedPostId
        val callCommentsList: Call<List<CommentsResponse>> = RetrofitClient.getClientForComments.retrieveComments(selectedPostId)
        callCommentsList.enqueue(object: Callback<List<CommentsResponse>> {
            override fun onResponse(call: Call<List<CommentsResponse>>, response: Response<List<CommentsResponse>>) {
                if (response != null && response.isSuccessful) {
                    CommentsSingleton.comments = response.body() as List<CommentsResponse>
                    NotificationCenter.postNotification(NotificationCenterType.ForDisplayComments,
                        "RetrieveCommentsSuccessful")

                } else {
                    NotificationCenter.postNotification(NotificationCenterType.ForDisplayComments,
                        "RetrieveCommentsFailed")
                }
            }

            override fun onFailure(call: Call<List<CommentsResponse>>, t: Throwable) {
                NotificationCenter.postNotification(NotificationCenterType.ForDisplayComments,
                    "RetrieveCommentsFailed")
            }
        })
    }
}
