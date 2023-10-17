package com.example.playlistmakerfirstproject.audioplayer.presentation.playlistDetails

import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track

sealed interface TracksInPlaylistState {

    object Loading : TracksInPlaylistState

    object Empty : TracksInPlaylistState

    data class Content(
        val tracks: List<Track>,
        val trackDurations : Int
    ) : TracksInPlaylistState

}