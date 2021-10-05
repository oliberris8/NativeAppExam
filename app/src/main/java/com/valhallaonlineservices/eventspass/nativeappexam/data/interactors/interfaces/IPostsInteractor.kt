package com.valhallaonlineservices.eventspass.nativeappexam.data.interactors.interfaces

import com.valhallaonlineservices.eventspass.nativeappexam.data.db.entities.PostsEntity

interface IPostsInteractor {
    fun insertPost(post: PostsEntity)

    fun insertPosts(posts: List<PostsEntity>)

    fun retrievePostsViaApi()
}
