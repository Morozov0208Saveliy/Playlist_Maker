package com.example.playlistmakerfirstproject.audioplayer.data.playlists.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.playlistmakerfirstproject.audioplayer.data.playlists.entity.PlaylistEntity
import com.example.playlistmakerfirstproject.audioplayer.data.playlists.entity.TrackInPlaylistsEntity

@Dao
interface PlaylistDao {

    @Insert(entity = PlaylistEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun createPlaylist(playlist: PlaylistEntity)

    @Query("DELETE FROM playlists WHERE id = :playlistId")
    suspend fun deletePlaylistById(playlistId: Int)


    @Query("SELECT * FROM playlists")
    suspend fun getAllPlaylists(): List<PlaylistEntity>

    @Query("SELECT * FROM playlists WHERE id = :id")
    suspend fun getPlaylistsById(id: Int): PlaylistEntity

    @Update
    suspend fun updatePlaylist(playlist: PlaylistEntity)


    @Query("UPDATE playlists SET id_of_tracks = :updatedListOfTracks, number_of_tracks = number_of_tracks - 1 WHERE id = :playlistId")
    suspend fun deleteTrackFromPlaylist(updatedListOfTracks: String?, playlistId: Int)

    @Query("SELECT * FROM tracks_in_playlists")
    suspend fun getAllTracksFromPlaylists(): List<TrackInPlaylistsEntity>?

    @Query("SELECT * FROM playlists WHERE id <> :excludedId")
    suspend fun getAllPlaylistsExceptOne(excludedId: Int): List<PlaylistEntity>


    @Query(
        """
    UPDATE playlists 
    SET 
        id_of_tracks = CASE 
                           WHEN id_of_tracks IS NULL OR id_of_tracks = '' 
                           THEN :trackId 
                           ELSE id_of_tracks || ',' || :trackId 
                       END,
        number_of_tracks = CASE 
                               WHEN number_of_tracks IS NULL 
                               THEN 1 
                               ELSE number_of_tracks + 1 
                           END
    WHERE id = :playlistId
"""
    )
    suspend fun addTrackToPlaylist(playlistId: Int, trackId: String)


}
