package com.example.playlistmakerfirstproject.audioplayer.domain

class MediaInteractorImpl(
    private val player: AudioPlayerRepository
) : MediaInteractor {

    override fun startPlaying() {
        player.startPlayer()
    }

    override fun pausePlaying() {
        player.pausePlayer()
    }

    override fun stopPlaying() {
        player.release()
    }

    override fun getPlayerPosition(): Int {
        return player.getCurrentPosition()
    }

    override fun getPlayerState(): StatePlayer {
        return player.statePlayer
    }
}