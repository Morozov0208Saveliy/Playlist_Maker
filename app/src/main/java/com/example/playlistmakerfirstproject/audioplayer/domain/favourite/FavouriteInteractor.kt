package com.example.playlistmakerfirstproject.audioplayer.domain.favourite

import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track
import kotlinx.coroutines.flow.Flow

interface FavouriteInteractor {
    interface FavouriteInteractor {
        fun getAllFavouriteTracks() : Flow<List<Track>>
        fun getIdOfFavouriteTracks() : Flow<List<Int>>
    }
}