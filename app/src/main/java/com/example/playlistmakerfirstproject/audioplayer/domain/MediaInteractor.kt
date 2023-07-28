package com.example.playlistmakerfirstproject.audioplayer.domain

interface MediaInteractor {
    fun startPlaying()
    fun pausePlaying()
    fun stopPlaying()
    fun getPlayerPosition(): Int
    fun getPlayerState(): StatePlayer
}