package com.valhallaonlineservices.eventspass.nativeappexam.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.valhallaonlineservices.eventspass.nativeappexam.constants.DatabaseRoomConstants

@Entity(tableName = DatabaseRoomConstants.TABLE_USERS, indices = [Index(value =
[DatabaseRoomConstants.COLUMN_USERS_ID, DatabaseRoomConstants.COLUMN_USERS_USERNAME], unique = true)])
data class UsersEntity(
    @ColumnInfo(name = DatabaseRoomConstants.COLUMN_USERS_ID)
    val id: Int,
    @ColumnInfo(name = DatabaseRoomConstants.COLUMN_USERS_NAME)
    val name: String,
    @ColumnInfo(name = DatabaseRoomConstants.COLUMN_USERS_USERNAME)
    val username: String,
    @ColumnInfo(name = DatabaseRoomConstants.COLUMN_USERS_EMAIL)
    val email: String,
    @ColumnInfo(name = DatabaseRoomConstants.COLUMN_USERS_ADDRESS_STREET)
    val addressStreet: String,
    @ColumnInfo(name = DatabaseRoomConstants.COLUMN_USERS_ADDRESS_SUITE)
    val addressSuite: String,
    @ColumnInfo(name = DatabaseRoomConstants.COLUMN_USERS_ADDRESS_CITY)
    val addressCity: String,
    @ColumnInfo(name = DatabaseRoomConstants.COLUMN_USERS_ADDRESS_ZIPCODE)
    val addressZipcode: String,
    @ColumnInfo(name = DatabaseRoomConstants.COLUMN_USERS_ADDRESS_LAT)
    val addressGeoLat: String,
    @ColumnInfo(name = DatabaseRoomConstants.COLUMN_USERS_ADDRESS_LNG)
    val addressGeoLng: String,
    @ColumnInfo(name = DatabaseRoomConstants.COLUMN_USERS_PHONE)
    val phone: String,
    @ColumnInfo(name = DatabaseRoomConstants.COLUMN_USERS_WEBSITE)
    val website: String,
    @ColumnInfo(name = DatabaseRoomConstants.COLUMN_USERS_COMPANY_NAME)
    val companyName: String,
    @ColumnInfo(name = DatabaseRoomConstants.COLUMN_USERS_COMPANY_CATCHPHRASE)
    val companyCatchphrase: String,
    @ColumnInfo(name = DatabaseRoomConstants.COLUMN_USERS_COMPANY_BS)
    val companyBs: String
) {
    @PrimaryKey(autoGenerate = true)
    var idUsers: Long? = null
}
