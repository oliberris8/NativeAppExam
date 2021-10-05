package com.valhallaonlineservices.eventspass.nativeappexam.data.interactors.implementations

import com.valhallaonlineservices.eventspass.nativeappexam.data.db.entities.PostsEntity
import com.valhallaonlineservices.eventspass.nativeappexam.data.interactors.interfaces.IPostsInteractor
import com.valhallaonlineservices.eventspass.nativeappexam.data.repositories.interfaces.IPostsRepository

class PostsInteractor(private val iPostsRepository: IPostsRepository) : IPostsInteractor {
    override fun insertPost(post: PostsEntity) {
        iPostsRepository.insertPost(post)
    }

    override fun insertPosts(posts: List<PostsEntity>) {
        iPostsRepository.insertPosts(posts)
    }

    override fun retrievePostsViaApi() {
        iPostsRepository.retrievePostsViaApi()
    }
}