package com.example.playlistmakerfirstproject.audioplayer.data.m_navigation.impl

import android.content.Context
import android.content.Intent
import com.example.playlistmakerfirstproject.audioplayer.data.history.impl.TRACK_TO_OPEN
import com.example.playlistmakerfirstproject.audioplayer.data.m_navigation.InternalNavigationRepository
import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track
import com.example.playlistmakerfirstproject.audioplayer.presentation.audioPlayer.AudioPlayerActivity

class InternalNavigationRepositoryImpl(private val context: Context) :
    InternalNavigationRepository {
    override fun openTrack(track: Track) {
        val displayIntent =
            Intent(context, AudioPlayerActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        displayIntent.putExtra(TRACK_TO_OPEN, track)
        context.startActivity(displayIntent)
    }

}



