package com.valhallaonlineservices.eventspass.nativeappexam.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.valhallaonlineservices.eventspass.nativeappexam.R
import com.valhallaonlineservices.eventspass.nativeappexam.enums.NotificationCenterType
import org.koin.dsl.module.Module

object NotificationCenter {
    private lateinit var context: Context
    fun addObserver(context: Context, notification: NotificationCenterType, responseHandler: BroadcastReceiver?) {
        this.context = context
        LocalBroadcastManager.getInstance(context)
            .registerReceiver(responseHandler!!, IntentFilter(notification.name))
    }

    fun removeObserver(context: Context, responseHandler: BroadcastReceiver?) {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(responseHandler!!)
    }

    fun postNotification(notification: NotificationCenterType, message: String) {
        val intent = Intent(notification.name)
        intent.putExtra(this.context.resources.getString(R.string.notification_center_message_key), message)
        LocalBroadcastManager.getInstance(this.context).sendBroadcast(intent)
    }
}