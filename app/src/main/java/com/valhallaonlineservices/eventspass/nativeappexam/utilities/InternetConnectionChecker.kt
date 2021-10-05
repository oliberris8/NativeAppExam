package com.valhallaonlineservices.eventspass.nativeappexam.utilities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import com.valhallaonlineservices.eventspass.nativeappexam.enums.ConnectivityType

class InternetConnectionChecker(context: Context) {
    private var mContext = context
    private var mState : ConnectivityType? = null

    private lateinit var networkCallback: ConnectivityManager.NetworkCallback

    var isAvailable: Boolean = false
    var connectivityType : ConnectivityType = ConnectivityType.Unknown

    fun register() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val connectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivityManager.activeNetwork == null) {
                isAvailable = false
            }

            networkCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onLost(network: Network) {
                    super.onLost(network)
                    mState = null
                    isAvailable = false
                }

                override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
                    super.onCapabilitiesChanged(network, networkCapabilities)
                    when {
                        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) && mState != ConnectivityType.Wifi -> {
                            isAvailable = true
                            mState = ConnectivityType.Wifi
                        }
                        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) && mState != ConnectivityType.Cellular -> {
                            isAvailable = true
                            mState = ConnectivityType.Cellular
                        }
                    }
                }
            }
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
        } else {
            IntentFilter("android.net.conn.CONNECTIVITY_CHANGE").apply {
                mContext.registerReceiver(networkChangeReceiver, this)
            }
        }
    }

    fun unregister() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val connectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            connectivityManager.unregisterNetworkCallback(networkCallback)
        } else {
            mContext.unregisterReceiver(networkChangeReceiver)
        }
    }

    fun getNetworkConnectionType() : ConnectivityType {
        when (connectivityType) {
            ConnectivityType.Wifi -> return ConnectivityType.Wifi
            ConnectivityType.Cellular -> return ConnectivityType.Cellular
            else -> return ConnectivityType.Unknown
        }
    }

    @Suppress("DEPRECATION")
    private val networkChangeReceiver : BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo

            if (activeNetworkInfo != null) {
                when (activeNetworkInfo.type) {
                    ConnectivityManager.TYPE_WIFI -> {
                        isAvailable = true
                        connectivityType = ConnectivityType.Wifi
                    }
                    else -> {
                        isAvailable = true
                        connectivityType = ConnectivityType.Cellular
                    }
                }
            } else {
                isAvailable = false
                connectivityType = ConnectivityType.Unknown
            }
        }
    }
}
