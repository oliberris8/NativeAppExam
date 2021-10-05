package com.valhallaonlineservices.eventspass.nativeappexam.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GeoModel (
    @Expose
    @SerializedName("lat")
    val lat: String,
    @Expose
    @SerializedName("lng")
    val lng: String
)
