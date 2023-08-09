package com.example.playlistmakerfirstproject.audioplayer.data

import com.example.playlistmakerfirstproject.audioplayer.data.dto.TrackSearchRequest
import com.example.playlistmakerfirstproject.audioplayer.data.dto.TrackSearchResponse
import com.example.playlistmakerfirstproject.audioplayer.domain.TrackRepository
import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track
import com.example.playlistmakerfirstproject.audioplayer.util.Resource

const val ERROR_NO_CONNECTION_TO_INTERNET =-1
const val SEARCH_SUCCESS =200

class TrackRepositoryImpl(private val networkClient: NetworkClient) : TrackRepository {

    override fun search(expression: String): Resource<List<Track>> {
        val response = networkClient.getTracksFromItunes(TrackSearchRequest(expression))
        return when (response.resultCode) {
            ERROR_NO_CONNECTION_TO_INTERNET -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            SEARCH_SUCCESS -> {
                Resource.Success((response as TrackSearchResponse).results.map {
                    Track(
                        it.trackName,
                        it.artistName,
                        it.trackTimeMillis,
                        it.artworkUrl100,
                        it.trackId,
                        it.collectionName,
                        it.releaseDate,
                        it.primaryGenreName,
                        it.country,
                        it.previewUrl
                    )
                })
            }
            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }
}