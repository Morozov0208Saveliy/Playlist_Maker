package com.example.playlistmakerfirstproject.audioplayer.domain.player

import com.example.playlistmakerfirstproject.audioplayer.domain.models.State
import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track

interface AudioPlayerInteractor {

    fun preparePlayer(url: String, onStateChanged: (s: State) -> Unit)
    fun pausePlayer()
    fun currentPosition(): Int
    fun switchPlayer(onStateChangedTo: (s: State) -> Unit)
    suspend fun addTrackToFav(track: Track)

    suspend fun deleteTrackFromFav(track: Track)


}