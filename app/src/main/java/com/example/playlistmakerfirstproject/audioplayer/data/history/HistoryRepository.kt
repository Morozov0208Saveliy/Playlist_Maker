package com.example.playlistmakerfirstproject.audioplayer.data.history

interface HistoryRepository {

    fun updateTrackHistory(tracksHistory: String)
    fun getHistoryString() : String
    fun clearHistory(): Unit
}