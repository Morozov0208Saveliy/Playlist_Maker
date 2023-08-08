package com.example.playlistmakerfirstproject.audioplayer.domain.player

import com.example.playlistmakerfirstproject.audioplayer.domain.models.State

interface MediaPlayerRepository {

    fun preparePlayer(url: String, onStateChangedTo: (s: State) -> Unit)

    fun currentPosition(): Int

    fun pause()

    fun switchPlayerState(onStateChangedTo: (s: State) -> Unit)


    fun exit()
}