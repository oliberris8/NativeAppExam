package com.valhallaonlineservices.eventspass.nativeappexam.data.apis

import com.valhallaonlineservices.eventspass.nativeappexam.constants.WebServiceConstants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface PostsRequest {
    @Headers("Content-Type: application/json")
    @GET(WebServiceConstants.GET_POSTS_API)
    fun retrievePosts() : Call<List<PostsResponse>>
}
