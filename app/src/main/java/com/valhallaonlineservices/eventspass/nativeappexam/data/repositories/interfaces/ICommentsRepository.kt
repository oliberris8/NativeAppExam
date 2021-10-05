package com.valhallaonlineservices.eventspass.nativeappexam.data.repositories.interfaces

import com.valhallaonlineservices.eventspass.nativeappexam.data.db.entities.CommentsEntity

interface ICommentsRepository {
    fun insertComment(comment: CommentsEntity)

    fun insertComments(comments: List<CommentsEntity>)

    fun retrieveCommentsViaApi()
}