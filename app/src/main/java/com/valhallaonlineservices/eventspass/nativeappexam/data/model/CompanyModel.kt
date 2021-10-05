package com.valhallaonlineservices.eventspass.nativeappexam.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CompanyModel(
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("catchPhrase")
    val catchPhrase: String,
    @Expose
    @SerializedName("bs")
    val bs: String
)
