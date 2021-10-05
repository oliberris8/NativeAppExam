package com.valhallaonlineservices.eventspass.nativeappexam.mvvms.viewmodels

import com.valhallaonlineservices.eventspass.nativeappexam.data.db.entities.CommentsEntity
import com.valhallaonlineservices.eventspass.nativeappexam.data.interactors.interfaces.ICommentsInteractor
import com.valhallaonlineservices.eventspass.nativeappexam.mvvms.models.Comments
import com.valhallaonlineservices.eventspass.nativeappexam.mvvms.views.DisplayCommentsContract
import com.valhallaonlineservices.eventspass.nativeappexam.singletons.CommentsSingleton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DisplayCommentsViewModel (private var viewDisplayComments: DisplayCommentsContract.DisplayCommentsView,
    private var iCommentsInteractor: ICommentsInteractor) :
    DisplayCommentsContract.DisplayCommentsViewModel {
    override fun retrieveComments() {
        iCommentsInteractor.retrieveCommentsViaApi()
        /*val selectedPostId = PostsSingleton.selectedPostId
        val callCommentsList: Call<List<CommentsResponse>> = RetrofitClient.getClientForComments.retrieveComments(selectedPostId)
        callCommentsList.enqueue(object: Callback<List<CommentsResponse>> {
            override fun onResponse(call: Call<List<CommentsResponse>>, response: Response<List<CommentsResponse>>) {
                if (response != null && response.isSuccessful) {
                    CommentsSingleton.comments = response.body() as List<CommentsResponse>
                    val filteredComments: List<Comments> = filterRetrievedComments()
                    if (viewDisplayComments != null) {
                        viewDisplayComments!!.retrieveCommentsSuccessful(filteredComments)
                    }
                } else {
                    if (viewDisplayComments != null) {
                        viewDisplayComments!!.retrieveCommentsFailed()
                    }
                }
            }

            override fun onFailure(call: Call<List<CommentsResponse>>, t: Throwable) {
                if (viewDisplayComments != null) {
                    viewDisplayComments!!.retrieveCommentsFailed()
                }
            }
        })*/
    }

    override fun showComments() {
        if (viewDisplayComments != null) {
            viewDisplayComments.displayComments()
        }
    }

    override fun filterRetrievedComments() {
        var filteredDataSet: ArrayList<Comments> = ArrayList()

        for (comment in CommentsSingleton.comments) {
            val username = comment.name
            val message = comment.body
            val modelDisplayComments = Comments(username, message)
            filteredDataSet.add(modelDisplayComments)
        }

        insertCommentsFromResponseToDatabase()
        if (viewDisplayComments != null) {
            viewDisplayComments!!.retrieveCommentsSuccessful(filteredDataSet.toList())
        }
    }

    private fun insertCommentsFromResponseToDatabase() {
        var comments: ArrayList<CommentsEntity> = ArrayList()
        for (comment in CommentsSingleton.comments) {
            val retrievedComment = CommentsEntity(comment.postId, comment.id, comment.name,
                comment.email, comment.body)
            comments.add(retrievedComment)
        }

        if (comments.size > 0) {
            GlobalScope.launch {
                iCommentsInteractor.insertComments(comments)
            }
        }
    }
}
