package com.example.playlistmakerfirstproject.audioplayer.domain.player

import com.example.playlistmakerfirstproject.audioplayer.domain.models.State
import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track

interface MediaPlayerRepository {

    fun preparePlayer(url: String, onStateChangedTo: (s: State) -> Unit)

    fun currentPosition(): Int

    fun pause()

    fun switchPlayerState(onStateChangedTo: (s: State) -> Unit)

    suspend fun saveTrackToFav(track: Track)

    suspend fun deleteTrackFromFav(track: Track)


    fun exit()
}