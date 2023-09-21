package com.example.playlistmakerfirstproject.audioplayer.domain.api

import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track
import kotlinx.coroutines.flow.Flow

interface TrackInteractor {
    fun search(expression: String): Flow<Pair<List<Track>?, String?>>

    fun loadTracks(onComplete : (Boolean) -> Unit)
}