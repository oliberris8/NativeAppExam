package com.valhallaonlineservices.eventspass.nativeappexam.singletons

import com.valhallaonlineservices.eventspass.nativeappexam.data.apis.PostsResponse

object PostsSingleton {
    var posts: List<PostsResponse>
    var selectedPostId: Int
    init {
        posts = ArrayList()
        selectedPostId = 0
    }
}
