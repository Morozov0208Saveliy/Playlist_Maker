package com.example.playlistmakerfirstproject.audioplayer.data.db.convertors

import com.example.playlistmakerfirstproject.audioplayer.data.playlists.entity.TrackInPlaylistsEntity
import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track

class TrackInPlaylistsEntityDbConvertor {

    fun map(track: Track): TrackInPlaylistsEntity {
        return TrackInPlaylistsEntity(
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

    fun map(track: TrackInPlaylistsEntity): Track {
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