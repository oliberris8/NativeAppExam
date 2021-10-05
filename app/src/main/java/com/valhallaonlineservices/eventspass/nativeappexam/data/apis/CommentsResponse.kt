package com.valhallaonlineservices.eventspass.nativeappexam.data.apis

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CommentsResponse (
    @Expose
    @SerializedName("postId")
    val postId: Int,
    @Expose
    @SerializedName("id")
    val id: String,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("email")
    val email: String,
    @Expose
    @SerializedName("body")
    val body: String,
)
