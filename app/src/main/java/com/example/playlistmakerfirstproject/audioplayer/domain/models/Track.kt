package com.example.playlistmakerfirstproject.audioplayer.domain.models

import com.example.playlistmakerfirstproject.audioplayer.presentation.audioPlayer.model.TrackInfo
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

data class Track(
    val trackName: String,
    val artistName: String,
    val trackTimeMillis: Int,
    val artUrl100: String,
    val trackId: Int,
    val collectionName: String,
    val releaseDate: String,
    val primaryGenreName: String,
    val country: String,
    val previewUrl: String?,
    var isFavorite: Boolean = false
) : Serializable {

    fun toTrackInfo() = TrackInfo(
        trackName = trackName,
        artistName = artistName,
        trackTime = SimpleDateFormat("mm:ss", Locale.getDefault()).format(trackTimeMillis.toLong()),
        artworkUrl100 = artUrl100.substringBeforeLast('/') + "512x512bb.jpg",
        trackId = trackId,
        collectionName = collectionName,
        releaseDate = getFormattedYear(),
        primaryGenreName = primaryGenreName,
        country = country,
        previewUrl = previewUrl ?: "",
        isFavorite = isFavorite
    )

    private fun getFormattedYear(): String {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val calendar: Calendar = Calendar.getInstance()
        calendar.time = format.parse(releaseDate)!!
        return calendar.get(Calendar.YEAR).toString()
    }
}
