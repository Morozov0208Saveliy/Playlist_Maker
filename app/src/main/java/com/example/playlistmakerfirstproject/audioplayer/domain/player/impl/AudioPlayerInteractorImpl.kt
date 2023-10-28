package com.example.playlistmakerfirstproject.audioplayer.domain.player.impl

import com.example.playlistmakerfirstproject.audioplayer.domain.models.State
import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track
import com.example.playlistmakerfirstproject.audioplayer.domain.player.AudioPlayerInteractor
import com.example.playlistmakerfirstproject.audioplayer.domain.player.MediaPlayerRepository

class AudioPlayerInteractorImpl(
    private val mediaPlayerRepository: MediaPlayerRepository
) : AudioPlayerInteractor {

    override fun preparePlayer(url: String, onStateChanged: (s: State) -> Unit) {
        mediaPlayerRepository.preparePlayer(
            url,
            onStateChanged
        )
    }

    override fun pausePlayer() {
        mediaPlayerRepository.pause()
    }

    override fun currentPosition(): Int {
        return mediaPlayerRepository.currentPosition()
    }

    override fun switchPlayer(onStateChangedTo: (s: State) -> Unit) {
        mediaPlayerRepository.switchPlayerState(onStateChangedTo)

    }

    override suspend fun addTrackToFav(track: Track) {
        mediaPlayerRepository.saveTrackToFav(track)
    }

    override suspend fun deleteTrackFromFav(track: Track) {
        mediaPlayerRepository.deleteTrackFromFav(track)
    }


}