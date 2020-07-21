@file:Suppress("BlockingMethodInNonBlockingContext")

package com.fuimonos.app.helpers

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

object ConnectivityHelper {

    private const val GOOGLE_PAGE_URL = "https://www.google.com"

    suspend fun isInternetAvailable(timeout: Int = 1500) = withContext(Dispatchers.IO) {
        try {
            val urlc = URL(GOOGLE_PAGE_URL).openConnection() as HttpURLConnection
            urlc.setRequestProperty("User-Agent", "Test")
            urlc.setRequestProperty("Connection", "close")
            urlc.connectTimeout = timeout
            urlc.connect()
            urlc.responseCode == 200
        } catch (e: IOException) {
            Timber.e(e)
            false
        }
    }

}
