package com.valhallaonlineservices.eventspass.nativeappexam.constants

object DatabaseRoomConstants {
    val DATABASE_VERSION = 1
    val DATABASE_NAME = "NativeAppExam.db"

    const val TABLE_COMMENTS = "comments"
    const val COLUMN_COMMENTS_POST_ID = "postId"
    const val COLUMN_COMMENTS_ID = "id"
    const val COLUMN_COMMENTS_NAME = "name"
    const val COLUMN_COMMENTS_EMAIL = "email"
    const val COLUMN_COMMENTS_BODY = "body"

    const val TABLE_POSTS = "posts"
    const val COLUMN_POSTS_USER_ID = "userId"
    const val COLUMN_POSTS_ID = "id"
    const val COLUMN_POSTS_TITLE = "title"
    const val COLUMN_POSTS_BODY = "body"

    const val TABLE_USERS = "users"
    const val COLUMN_USERS_ID = "id"
    const val COLUMN_USERS_NAME = "name"
    const val COLUMN_USERS_USERNAME = "username"
    const val COLUMN_USERS_EMAIL = "email"
    const val COLUMN_USERS_ADDRESS_STREET = "street"
    const val COLUMN_USERS_ADDRESS_SUITE = "suite"
    const val COLUMN_USERS_ADDRESS_CITY = "city"
    const val COLUMN_USERS_ADDRESS_ZIPCODE = "zipcode"
    const val COLUMN_USERS_ADDRESS_LAT = "lat"
    const val COLUMN_USERS_ADDRESS_LNG = "lng"
    const val COLUMN_USERS_PHONE = "phone"
    const val COLUMN_USERS_WEBSITE = "website"
    const val COLUMN_USERS_COMPANY_NAME = "companyName"
    const val COLUMN_USERS_COMPANY_CATCHPHRASE = "catchPhrase"
    const val COLUMN_USERS_COMPANY_BS = "bs"
}