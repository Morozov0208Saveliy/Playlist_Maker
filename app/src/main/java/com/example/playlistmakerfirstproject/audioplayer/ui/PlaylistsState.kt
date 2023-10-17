package com.example.playlistmakerfirstproject.audioplayer.ui

import com.example.playlistmakerfirstproject.audioplayer.domain.models.Playlist

sealed interface PlaylistsState {

    object Loading : PlaylistsState

    object Empty : PlaylistsState


    data class Content(
        val playlists: List<Playlist>
    ) : PlaylistsState

}