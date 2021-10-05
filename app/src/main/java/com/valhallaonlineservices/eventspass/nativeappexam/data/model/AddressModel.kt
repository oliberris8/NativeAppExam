package com.valhallaonlineservices.eventspass.nativeappexam.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AddressModel (
    @Expose
    @SerializedName("street")
    val street: String,
    @Expose
    @SerializedName("suite")
    val suite: String,
    @Expose
    @SerializedName("city")
    val city: String,
    @Expose
    @SerializedName("zipcode")
    val zipcode: String,
    @Expose
    @SerializedName("geo")
    val geo: GeoModel
)
