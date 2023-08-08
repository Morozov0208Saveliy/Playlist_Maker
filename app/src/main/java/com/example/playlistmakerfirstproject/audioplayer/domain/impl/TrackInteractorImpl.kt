package com.example.playlistmakerfirstproject.audioplayer.domain.impl

import com.example.playlistmakerfirstproject.audioplayer.data.m_navigation.InternalNavigationRepository
import com.example.playlistmakerfirstproject.audioplayer.domain.TrackRepository
import com.example.playlistmakerfirstproject.audioplayer.domain.api.TrackInteractor
import com.example.playlistmakerfirstproject.audioplayer.domain.m_navigation.InternalNavigationInteractor
import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track
import com.example.playlistmakerfirstproject.audioplayer.util.Resource
import java.util.concurrent.Executors


class TrackInteractorImpl(private val repository: TrackRepository) : TrackInteractor {

    private val executor = Executors.newCachedThreadPool()

    override fun search(expression: String, consumer: TrackInteractor.TrackConsumer) {
        executor.execute {
            when(val resource = repository.search(expression)) {
                is Resource.Success -> { consumer.consume(resource.data, null) }
                is Resource.Error -> { consumer.consume(null, resource.message) }
            }
        }
    }

    override fun loadTracks(onComplete: (Boolean) -> Unit) {
        onComplete(true)
    }
}