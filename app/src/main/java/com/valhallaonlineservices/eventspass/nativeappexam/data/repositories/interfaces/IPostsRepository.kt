package com.valhallaonlineservices.eventspass.nativeappexam.data.repositories.interfaces

import com.valhallaonlineservices.eventspass.nativeappexam.data.db.entities.PostsEntity

interface IPostsRepository {
    fun insertPost(post: PostsEntity)

    fun insertPosts(posts: List<PostsEntity>)

    fun retrievePostsViaApi()
}
