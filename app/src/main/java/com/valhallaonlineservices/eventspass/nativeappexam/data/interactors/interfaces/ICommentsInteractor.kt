package com.valhallaonlineservices.eventspass.nativeappexam.data.interactors.interfaces

import com.valhallaonlineservices.eventspass.nativeappexam.data.db.entities.CommentsEntity

interface ICommentsInteractor {
    fun insertComment(comment: CommentsEntity)

    fun insertComments(comments: List<CommentsEntity>)

    fun retrieveCommentsViaApi()
}
