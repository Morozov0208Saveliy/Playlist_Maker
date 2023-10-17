package com.example.playlistmakerfirstproject.audioplayer.presentation.m_navigation.impl

import com.example.playlistmakerfirstproject.audioplayer.data.m_navigation.InternalNavigationRepository
import com.example.playlistmakerfirstproject.audioplayer.presentation.m_navigation.InternalNavigationInteractor
import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track

class InternalNavigationInteractorImpl(private val internalNavigationRepository: InternalNavigationRepository) :
    InternalNavigationInteractor {
    override fun openTrack(track: Track) {
        internalNavigationRepository.openTrack(track)
    }

}

