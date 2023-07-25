package com.example.playlistmakerfirstproject.audioplayer.presentation.presenters

import androidx.appcompat.app.AppCompatActivity
import com.example.playlistmakerfirstproject.audioplayer.presentation.ui.AudioPlayerActivity
import com.example.playlistmakerfirstproject.audioplayer.domain.Track
import com.google.gson.Gson

object TrackObject {
    fun getTrack(activity: AppCompatActivity): Track {
        return Gson().fromJson((activity.intent.getStringExtra(AudioPlayerActivity.TRACK_OBJECT)),
            Track::class.java)
    }
}