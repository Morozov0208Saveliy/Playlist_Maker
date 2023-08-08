package com.example.playlistmakerfirstproject.audioplayer.domain.api

import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track

interface TrackInteractor {
    fun search(expression: String, consumer: TrackConsumer)

    interface TrackConsumer {
        fun consume(foundTracks: List<Track>?, errorMessage:String?)
    }

    fun loadTracks(onComplete : (Boolean) -> Unit)
}