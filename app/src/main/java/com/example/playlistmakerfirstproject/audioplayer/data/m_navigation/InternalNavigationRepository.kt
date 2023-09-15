package com.example.playlistmakerfirstproject.audioplayer.data.m_navigation

import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track

interface InternalNavigationRepository {

    fun openTrack(track: Track)

}