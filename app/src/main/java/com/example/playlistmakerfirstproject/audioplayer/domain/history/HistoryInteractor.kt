package com.example.playlistmakerfirstproject.audioplayer.domain.history

import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track

interface HistoryInteractor {


    fun addTrackToHistory(track: Track)


    fun getHistoryString(): String?

    fun clearHistory(): Unit

    fun updateHistory(updatedHistoryList: ArrayList<Track>): Unit

    fun getHistoryList(): ArrayList<Track>

}