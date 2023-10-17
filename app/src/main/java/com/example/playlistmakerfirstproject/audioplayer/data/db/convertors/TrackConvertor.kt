package com.example.playlistmakerfirstproject.audioplayer.data.db.convertors

import com.example.playlistmakerfirstproject.audioplayer.data.favourites.TrackEntity
import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track

class TrackConvertor {

    fun map(track: Track) : TrackEntity {
        return TrackEntity(
            track.trackId,
            track.artworkUrl100,
            track.trackName,
            track.artistName,
            track.collectionName,
            track.releaseDate,
            track.primaryGenreName,
            track.country,
            track.trackTimeMillis,
            track.previewUrl
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