package com.example.playlistmakerfirstproject.audioplayer.domain.player

import com.example.playlistmakerfirstproject.audioplayer.domain.models.State

interface AudioPlayerInteractor {

    fun preparePlayer(url: String, onStateChanged: (s: State) -> Unit)
    fun pausePlayer()
    fun currentPosition(): Int
    fun switchPlayer(onStateChangedTo: (s: State) -> Unit)


}