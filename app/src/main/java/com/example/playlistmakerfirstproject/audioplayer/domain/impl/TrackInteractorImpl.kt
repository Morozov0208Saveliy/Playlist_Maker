package com.example.playlistmakerfirstproject.audioplayer.domain.impl

import com.example.playlistmakerfirstproject.audioplayer.data.m_navigation.InternalNavigationRepository
import com.example.playlistmakerfirstproject.audioplayer.domain.TrackRepository
import com.example.playlistmakerfirstproject.audioplayer.domain.api.TrackInteractor
import com.example.playlistmakerfirstproject.audioplayer.domain.m_navigation.InternalNavigationInteractor
import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track
import com.example.playlistmakerfirstproject.audioplayer.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.Executors


class TrackInteractorImpl(private val repository: TrackRepository) : TrackInteractor {

    override fun search(expression: String): Flow<Pair<List<Track>?, String?>> {
        return repository.search(expression).map { result ->
            when(result) {
                is Resource.Success -> {
                    Pair(result.data,null)
                }
                is Resource.Error -> {
                    Pair(null, result.message)
                }
            }
        }
    }

    override fun loadTracks(onComplete: (Boolean) -> Unit) {
        onComplete(true)
    }
}