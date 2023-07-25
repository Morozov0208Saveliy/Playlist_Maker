package com.example.playlistmakerfirstproject.audioplayer.presentation.presenters

import com.example.playlistmakerfirstproject.audioplayer.domain.Track

interface AudioPlayerView {
    fun createTrack(track: Track, startPosition: Int)
    fun updatePlayButton(imageResource: Int)
    fun updateTrackDuration(currentPositionMediaPlayer: Int)
}