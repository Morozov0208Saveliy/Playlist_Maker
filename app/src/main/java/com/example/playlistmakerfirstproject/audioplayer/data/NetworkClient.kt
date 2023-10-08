package com.example.playlistmakerfirstproject.audioplayer.data

import com.example.playlistmakerfirstproject.audioplayer.data.dto.Response

interface NetworkClient {

    suspend fun getTracksFromItunes(dto: Any): Response
}