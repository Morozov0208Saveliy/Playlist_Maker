package com.example.playlistmakerfirstproject.audioplayer.data.player

import android.media.MediaPlayer
import com.example.playlistmakerfirstproject.audioplayer.data.db.AppDatabase
import com.example.playlistmakerfirstproject.audioplayer.data.db.TrackConvertor
import com.example.playlistmakerfirstproject.audioplayer.domain.models.State
import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track
import com.example.playlistmakerfirstproject.audioplayer.domain.player.MediaPlayerRepository

class MediaPlayerRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val trackDbConvertor: TrackConvertor,
) : MediaPlayerRepository {

    private val mediaPlayer: MediaPlayer = MediaPlayer()
    private var playerState = State.PREPARED
    override fun preparePlayer(
        url: String,
        onStateChangedTo: (s: State) -> Unit
    ) {
        mediaPlayer.reset()
        mediaPlayer.setDataSource(url)
        mediaPlayer.prepareAsync()
        mediaPlayer.setOnPreparedListener {
            playerState = State.PREPARED
            onStateChangedTo(State.PREPARED)
        }
        mediaPlayer.setOnCompletionListener {
            playerState = State.PREPARED
            onStateChangedTo(State.PREPARED)
        }
        mediaPlayer.reset()
        mediaPlayer.setDataSource(url)
        mediaPlayer.prepareAsync()
    }

    override suspend fun saveTrackToFav(track: Track) {
        var trackEntities = trackDbConvertor.map(track)
        appDatabase.trackDao().insertTrack(trackEntities)
    }

    override suspend fun deleteTrackFromFav(track: Track) {
        val trackEntities = trackDbConvertor.map(track)
        appDatabase.trackDao().deleteTrack(trackEntities)
    }


    override fun pause() {
        this.mediaPlayer.pause()
        playerState = State.PREPARED
    }

    override fun currentPosition(): Int {
        return mediaPlayer.currentPosition
    }

    override fun switchPlayerState(onStateChangedTo: (s: State) -> Unit) {
        when (playerState) {
            State.DEFAULT -> {}
            State.PREPARED, State.PAUSED -> {
                mediaPlayer.start()
                playerState = State.PLAYING
                onStateChangedTo(State.PLAYING)
            }

            State.PLAYING -> {
                mediaPlayer.pause()
                playerState = State.PAUSED
                onStateChangedTo(State.PAUSED)
            }
        }
    }

    override fun exit() {
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}
