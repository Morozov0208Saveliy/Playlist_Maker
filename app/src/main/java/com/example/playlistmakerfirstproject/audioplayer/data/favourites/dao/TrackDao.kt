package com.example.playlistmakerfirstproject.audioplayer.data.favourites.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.playlistmakerfirstproject.audioplayer.data.favourites.entity.TrackEntity


@Dao
interface TrackDao {

    @Insert(entity = TrackEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrack(track: TrackEntity)

    @Delete(entity = TrackEntity::class)
    suspend fun deleteTrack(track: TrackEntity)

    @Query("SELECT * FROM favourite_tracks  ORDER BY timestamp DESC ")
    suspend fun getAllFavouriteTracks(): List<TrackEntity>

    @Query("SELECT track_id FROM favourite_tracks")
    suspend fun getIdsOfFavouriteTracks(): List<Int>
}