package com.valhallaonlineservices.eventspass.nativeappexam.data.interactors.implementations

import com.valhallaonlineservices.eventspass.nativeappexam.data.db.entities.CommentsEntity
import com.valhallaonlineservices.eventspass.nativeappexam.data.interactors.interfaces.ICommentsInteractor
import com.valhallaonlineservices.eventspass.nativeappexam.data.repositories.interfaces.ICommentsRepository

class CommentsInteractor(private val iCommentsRepository: ICommentsRepository) : ICommentsInteractor {
    override fun insertComment(comment: CommentsEntity) {
        iCommentsRepository.insertComment(comment)
    }

    override fun insertComments(comments: List<CommentsEntity>) {
        iCommentsRepository.insertComments(comments)
    }

    override fun retrieveCommentsViaApi() {
        iCommentsRepository.retrieveCommentsViaApi()
    }
}