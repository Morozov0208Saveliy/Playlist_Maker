package com.example.playlistmakerfirstproject.audioplayer.domain.impl

import com.example.playlistmakerfirstproject.audioplayer.domain.db.FavouriteInteractor
import com.example.playlistmakerfirstproject.audioplayer.domain.db.FavouriteRepository
import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track
import kotlinx.coroutines.flow.Flow


class FavouriteInteractorImpl(
    private val favouriteRepository: FavouriteRepository
) : FavouriteInteractor.FavouriteInteractor {
    override fun getAllFavouriteTracks(): Flow<List<Track>> {
        return favouriteRepository.getAllFavouriteTracks()
    }

    override fun getIdOfFavouriteTracks(): Flow<List<Int>> {
        return favouriteRepository.getFavouriteIndicators()
    }

}