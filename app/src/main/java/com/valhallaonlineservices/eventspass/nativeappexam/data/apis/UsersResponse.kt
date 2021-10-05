package com.valhallaonlineservices.eventspass.nativeappexam.data.apis

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.valhallaonlineservices.eventspass.nativeappexam.data.model.AddressModel
import com.valhallaonlineservices.eventspass.nativeappexam.data.model.CompanyModel

data class UsersResponse (
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("username")
    val username: String,
    @Expose
    @SerializedName("email")
    val email: String,
    @Expose
    @SerializedName("address")
    val address: AddressModel,
    @Expose
    @SerializedName("phone")
    val phone: String,
    @Expose
    @SerializedName("website")
    val website: String,
    @Expose
    @SerializedName("company")
    val company: CompanyModel
)
