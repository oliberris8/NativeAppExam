package com.valhallaonlineservices.eventspass.nativeappexam.mvvms.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Comments(
    val username: String,
    val message: String
)
