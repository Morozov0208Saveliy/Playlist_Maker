package com.example.playlistmakerfirstproject.audioplayer.data.db.convertors

import com.example.playlistmakerfirstproject.audioplayer.data.favourites.entity.TrackEntity
import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track

class TrackDbConvertor {

    fun map(track: Track): TrackEntity {
        return TrackEntity(
            id = track.trackId,
            artworkUrl100 = track.artUrl100,
            trackName = track.trackName,
            artistName = track.artistName,
            collectionName = track.collectionName,
            releaseDate = track.releaseDate,
            primaryGenreName = track.primaryGenreName,
            country = track.country,
            trackTimeMillis = track.trackTimeMillis,
            previewUrl = track.previewUrl ?: ""
        )
    }

    fun map(track: TrackEntity): Track {
        return Track(
            track.trackName,
            track.artistName,
            track.trackTimeMillis,
            track.artworkUrl100,
            track.id,
            track.collectionName,
            track.releaseDate,
            track.primaryGenreName,
            track.country,
            track.previewUrl
        )
    }

}