package com.example.playlistmakerfirstproject.audioplayer.domain.models

import com.example.playlistmakerfirstproject.audioplayer.presentation.audioPlayer.model.TrackInfo
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

data class Track(
    val trackName: String,
    val artistName: String,
    val trackTimeMillis: Int,
    val artworkUrl100: String,
    val trackId: Int,
    val collectionName: String,
    val releaseDate: String,
    val primaryGenreName: String,
    val country: String,
    val previewUrl: String,
    var isFavorite: Boolean = false
): Serializable {

    fun toTrackInfo() = TrackInfo(
        trackName = this.trackName,
        artistName = this.artistName,
        trackTime = SimpleDateFormat("mm:ss", Locale.getDefault()).format(this.trackTimeMillis.toLong()),
        artworkUrl100 = this.artworkUrl100.replaceAfterLast('/', "512x512bb.jpg"),
        trackId = this.trackId,
        collectionName = this.collectionName,
        releaseDate = getFormattedYear(),
        primaryGenreName = this.primaryGenreName,
        country = this.country,
        previewUrl = this.previewUrl,
        isFavorite = this.isFavorite
    )

    private fun getFormattedYear(): String {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val calendar: Calendar = Calendar.getInstance()
        calendar.time = format.parse(this.releaseDate)!!
        return calendar.get(Calendar.YEAR).toString()
    }
}
