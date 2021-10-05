package com.valhallaonlineservices.eventspass.nativeappexam.data.apis

import com.valhallaonlineservices.eventspass.nativeappexam.constants.WebServiceConstants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface CommentsRequest {
    @Headers("Content-Type: application/json")
    @GET(WebServiceConstants.GET_POSTS_API + "/{id}" + WebServiceConstants.GET_COMMENTS_API)
    fun retrieveComments(@Path("id") id: Int) : Call<List<CommentsResponse>>
}
