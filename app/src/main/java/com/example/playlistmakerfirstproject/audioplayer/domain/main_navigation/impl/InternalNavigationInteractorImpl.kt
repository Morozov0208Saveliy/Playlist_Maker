package com.example.playlistmakerfirstproject.audioplayer.domain.main_navigation.impl

import com.example.playlistmakerfirstproject.audioplayer.data.m_navigation.InternalNavigationRepository
import com.example.playlistmakerfirstproject.audioplayer.domain.main_navigation.InternalNavigationInteractor
import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track

class InternalNavigationInteractorImpl(private val internalNavigationRepository: InternalNavigationRepository) :
    InternalNavigationInteractor {

    // Correctly override the openTrack method from the interface
    override fun openTrack(track: Track) {
        internalNavigationRepository.openTrack(track)
    }
}
