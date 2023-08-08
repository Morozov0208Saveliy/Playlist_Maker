package com.example.playlistmakerfirstproject.audioplayer.presentation.ui

import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track

data class TracksState(

    val tracks: List<Track>,
    val isLoading: Boolean,
    val placeholderMessage: Int?,
    val needToUpdate:Boolean,
    val toShowHistory:Boolean,
    val history: List<Track>

)