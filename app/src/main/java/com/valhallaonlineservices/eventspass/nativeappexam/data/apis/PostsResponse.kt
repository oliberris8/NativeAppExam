package com.valhallaonlineservices.eventspass.nativeappexam.data.apis

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostsResponse (
    @Expose
    @SerializedName("userId")
    val userId: Int,
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("title")
    val title: String,
    @Expose
    @SerializedName("body")
    val body: String,
)
