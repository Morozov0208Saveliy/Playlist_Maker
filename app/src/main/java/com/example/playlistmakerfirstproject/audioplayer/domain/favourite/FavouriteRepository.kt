package com.example.playlistmakerfirstproject.audioplayer.domain.favourite

import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track
import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {
    fun getAllFavouriteTracks(): Flow<List<Track>>
    fun getFavouriteIndicators(): Flow<List<Int>>
}