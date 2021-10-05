package com.valhallaonlineservices.eventspass.nativeappexam.mvvms.views

import com.valhallaonlineservices.eventspass.nativeappexam.mvvms.models.Comments

interface DisplayCommentsContract {
    interface DisplayCommentsView {
        fun retrieveCommentsSuccessful(dataComments: List<Comments>)

        fun retrieveCommentsFailed()

        fun displayComments()
    }
    interface DisplayCommentsViewModel {
        fun retrieveComments()

        fun showComments()

        fun filterRetrievedComments()
    }
}
