package com.example.playlistmakerfirstproject.audioplayer.domain.api

import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track
import com.example.playlistmakerfirstproject.audioplayer.util.Resource
import kotlinx.coroutines.flow.Flow

interface TrackRepository {
    fun search(expression: String): Flow<Resource<List<Track>>>
    fun getFavIndicators() : Flow<List<Int>>
}
