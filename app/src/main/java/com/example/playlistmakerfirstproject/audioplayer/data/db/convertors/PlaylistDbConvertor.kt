package com.example.playlistmakerfirstproject.audioplayer.data.db.convertors

import com.example.playlistmakerfirstproject.audioplayer.data.playlists.entity.PlaylistEntity
import com.example.playlistmakerfirstproject.audioplayer.domain.models.Playlist
import com.google.gson.Gson

class PlaylistDbConvertor {

    fun map(playlist: Playlist) : PlaylistEntity {
        return PlaylistEntity(
            playlist.id,
            playlist.name,
            playlist.details,
            playlist.imagePath,
            convertIdOfTrackToStringNoGson(playlist.idOfTracks),
            playlist.idOfTracks?.size
        )
    }
    fun map(playlist: PlaylistEntity): Playlist {
        val idOfTracksList = if (playlist.idOfTracks != "null" && !playlist.idOfTracks.isNullOrEmpty()) {
            convertStringOfIdTrackToList(playlist.idOfTracks)
        } else {
            emptyList()
        }

        return Playlist(
            playlist.id,
            playlist.name,
            playlist.details,
            playlist.imagePath,
            idOfTracksList,
            playlist.numberOfTracks ?: 0
        )
    }


    fun convertIdOfTrackToString(listOfIds: List<Int>?): String?{
        return if (listOfIds ==null) {
            null
        } else {
            Gson().toJson(listOfIds)
        }
    }
    fun convertIdOfTrackToStringNoGson(listOfIds: List<Int>?): String? {
        return listOfIds?.joinToString(separator = ",")
    }
    fun convertStringOfIdTrackToList(stringOfIds: String?): List<Int> {
        return stringOfIds?.split(",")?.mapNotNull { it.toIntOrNull() } ?: emptyList()
    }


}