package com.example.playlistmakerfirstproject.audioplayer.data.dto

import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track

data class TrackSearchResponse(val results: List<TrackDto>) : Response()
