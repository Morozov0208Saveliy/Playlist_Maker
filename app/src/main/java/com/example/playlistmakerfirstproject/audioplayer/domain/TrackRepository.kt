package com.example.playlistmakerfirstproject.audioplayer.domain

import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track
import com.example.playlistmakerfirstproject.audioplayer.util.Resource

interface TrackRepository {
    fun search(expression: String): Resource<List<Track>>
}
