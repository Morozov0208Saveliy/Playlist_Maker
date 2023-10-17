package com.example.playlistmakerfirstproject.audioplayer.presentation.ui

import com.example.playlistmakerfirstproject.audioplayer.domain.models.Playlist


sealed interface ResultOfAddingState {

    data class SUCCESS(
        val playlists: Playlist
    ) : ResultOfAddingState

    data class ALREADY_EXISTS(
        val playlists: Playlist
    ) : ResultOfAddingState

}