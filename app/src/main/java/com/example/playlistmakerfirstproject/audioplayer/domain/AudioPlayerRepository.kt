package com.example.playlistmakerfirstproject.audioplayer.domain

interface AudioPlayerRepository {

    var statePlayer: StatePlayer
    fun getCurrentPosition(): Int
    fun startPlayer()
    fun pausePlayer()
    fun release()
}