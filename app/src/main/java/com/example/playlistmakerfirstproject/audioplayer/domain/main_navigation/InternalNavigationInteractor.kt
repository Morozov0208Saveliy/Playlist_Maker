package com.example.playlistmakerfirstproject.audioplayer.domain.main_navigation

import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track

interface InternalNavigationInteractor {
    fun openTrack(track: Track)
}