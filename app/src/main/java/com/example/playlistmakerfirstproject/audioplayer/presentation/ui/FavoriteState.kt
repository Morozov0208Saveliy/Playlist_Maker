package com.example.playlistmakerfirstproject.audioplayer.presentation.ui

import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track

sealed interface FavoriteState {

    object Loading : FavoriteState

    data class Content(
        val tracks: List<Track>
    ) : FavoriteState

    data class Empty(
        val message: String
    ) : FavoriteState
}