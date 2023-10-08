package com.example.playlistmakerfirstproject.audioplayer.domain.db

import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track
import kotlinx.coroutines.flow.Flow

interface FavouriteInteractor {
    interface FavouriteInteractor {
        fun getAllFavouriteTracks() : Flow<List<Track>>
        fun getIdOfFavouriteTracks() : Flow<List<Int>>
    }
}