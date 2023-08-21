package com.example.playlistmakerfirstproject.audioplayer.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.playlistmakerfirstproject.audioplayer.data.ERROR_NO_CONNECTION_TO_INTERNET
import com.example.playlistmakerfirstproject.audioplayer.data.NetworkClient
import com.example.playlistmakerfirstproject.audioplayer.data.dto.Response
import com.example.playlistmakerfirstproject.audioplayer.data.dto.TrackSearchRequest

class RetrofitNetworkClient(private val imdbService: Itunes,
                            private val context: Context)  : NetworkClient {

    override fun getTracksFromItunes(dto: Any): Response {
        if (!isConnected()){
            return Response().apply { resultCode = ERROR_NO_CONNECTION_TO_INTERNET }
        }
        if (dto !is TrackSearchRequest) {
            return Response().apply { resultCode = 400 }
        }
        val resp = imdbService.search(dto.expression).execute()
        val body = resp.body() ?: Response()
        return if (body != null) {
            body.apply { resultCode = resp.code() }
        } else {
            Response().apply { resultCode = resp.code() }
        }
    }

    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }
}