package com.example.playlistmakerfirstproject.audioplayer.data.db.favourites

import com.example.playlistmakerfirstproject.audioplayer.data.db.AppDatabase
import com.example.playlistmakerfirstproject.audioplayer.data.db.TrackConvertor
import com.example.playlistmakerfirstproject.audioplayer.data.db.entity.TrackEntity
import com.example.playlistmakerfirstproject.audioplayer.domain.db.FavouriteRepository
import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FavouriteRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val trackDbConvertor: TrackConvertor,
) : FavouriteRepository {
    override fun getAllFavouriteTracks(): Flow<List<Track>>  = flow {
        val tracks = appDatabase.trackDao().getAllFavouriteTracks()
        emit(convertFromTrackEntity(tracks))
    }
    private fun convertFromTrackEntity(tracks: List<TrackEntity>): List<Track> {
        return tracks.map { track -> trackDbConvertor.map(track) }
    }
    override fun getFavouriteIndicators() : Flow<List<Int>> = flow {
        val listOfIdFavTracks = appDatabase.trackDao().getIdsOfFavouriteTracks()
        emit(listOfIdFavTracks)
    }
}