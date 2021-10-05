package com.valhallaonlineservices.eventspass.nativeappexam.singletons

import com.valhallaonlineservices.eventspass.nativeappexam.data.apis.CommentsResponse

object CommentsSingleton {
    var comments: List<CommentsResponse>
    init {
        comments = ArrayList()
    }
}
