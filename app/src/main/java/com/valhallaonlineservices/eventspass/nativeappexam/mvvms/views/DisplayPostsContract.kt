package com.valhallaonlineservices.eventspass.nativeappexam.mvvms.views

import com.valhallaonlineservices.eventspass.nativeappexam.mvvms.models.Posts

interface DisplayPostsContract {
    interface DisplayPostsView {
        fun retrieveUsersSuccessful()

        fun retrieveUsersFailed()

        fun retrievePostsSuccessful(dataPosts: List<Posts>)

        fun retrievePostsFailed()

        fun displayPosts()
    }
    interface DisplayPostsViewModel {
        fun retrieveUsers()

        fun retrievePosts()

        fun showPosts()

        fun continueRetrieval()

        fun filterRetrievedPosts()
    }
}
