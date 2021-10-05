package com.valhallaonlineservices.eventspass.nativeappexam.services

import com.google.gson.GsonBuilder
import com.valhallaonlineservices.eventspass.nativeappexam.data.apis.CommentsRequest
import com.valhallaonlineservices.eventspass.nativeappexam.data.apis.PostsRequest
import com.valhallaonlineservices.eventspass.nativeappexam.data.apis.UsersRequest
import com.valhallaonlineservices.eventspass.nativeappexam.constants.WebServiceConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private val gson = GsonBuilder()
        .setLenient()
        .create()
    private val interceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)
    private val client = OkHttpClient.Builder()
        .callTimeout(2, TimeUnit.MINUTES)
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(40, TimeUnit.SECONDS)
        .writeTimeout(40, TimeUnit.SECONDS).
        addInterceptor(interceptor).build()
    private val retrofit = Retrofit.Builder()
        .baseUrl(WebServiceConstants.BASE_URL)
        .client(client)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()


    val getClientForUsers: UsersRequest
        get() {
            return retrofit.create(UsersRequest::class.java)
        }

    val getClientForPosts: PostsRequest
        get() {
            return retrofit.create(PostsRequest::class.java)
        }

    val getClientForComments: CommentsRequest
        get() {
            return retrofit.create(CommentsRequest::class.java)
        }
}
