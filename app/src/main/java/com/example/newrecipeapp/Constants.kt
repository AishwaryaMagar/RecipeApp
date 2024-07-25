package com.example.newrecipeapp

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object Constants {

    const val APP_ID: String = "0fa516aa"
    const val APP_KEY: String = "3e5a7a6c2596b9c0be04bb0142effd70"
    const val BASE_URL: String = "https://api.edamam.com/"
    @SuppressLint("SuspiciousIndentation")
    fun isNetworkAvailable(context: Context): Boolean{
        val connectivityManager = context.
        getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            val network = connectivityManager.activeNetwork?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when{
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }

        }
        else{
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnectedOrConnecting
        }
    }
}