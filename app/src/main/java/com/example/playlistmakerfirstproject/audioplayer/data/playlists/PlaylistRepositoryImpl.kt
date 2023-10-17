package com.example.playlistmakerfirstproject.audioplayer.data.playlists

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.example.playlistmakerfirstproject.audioplayer.data.db.AppDatabase
import com.example.playlistmakerfirstproject.audioplayer.data.db.convertors.PlaylistDbConvertor
import com.example.playlistmakerfirstproject.audioplayer.data.db.convertors.TrackInPlaylistsEntityDbConvertor
import com.example.playlistmakerfirstproject.audioplayer.data.playlists.entity.PlaylistEntity
import com.example.playlistmakerfirstproject.audioplayer.data.playlists.entity.TrackInPlaylistsEntity
import com.example.playlistmakerfirstproject.audioplayer.domain.models.Playlist
import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track
import com.example.playlistmakerfirstproject.audioplayer.domain.playlists.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import java.io.FileOutputStream
import java.util.UUID

class PlaylistRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val playlistDbConvertor: PlaylistDbConvertor,
    private val trackInPlaylistsEntityDbConvertor: TrackInPlaylistsEntityDbConvertor,
    private val contentResolver: ContentResolver
) : PlaylistRepository {


    override fun saveImageFromUri(uri: Uri, picturesDirectoryPath: String): String {
        val uniqueID = UUID.randomUUID().toString()
        val file = File(picturesDirectoryPath, "cover_$uniqueID.jpg")

        val inputStream = contentResolver.openInputStream(uri)
        val outputStream = FileOutputStream(file)

        BitmapFactory.decodeStream(inputStream)
            .compress(Bitmap.CompressFormat.JPEG, 30, outputStream)
        inputStream?.close()
        outputStream.close()

        return file.absolutePath
    }


    override fun getTracksOnlyFromPlaylist(listOfId: List<Int>): Flow<List<Track>?> = flow {
        val tracksInAllPlaylists = appDatabase.trackInPlaylistDao().getAllTracksFromPlaylists()

        if (tracksInAllPlaylists == null) {
            emit(null)
        } else {
            val filteredTracks = tracksInAllPlaylists.filter { track ->
                listOfId.contains(track.id)
            }

            val sortedTracks = filteredTracks.sortedBy { track ->
                listOfId.indexOf(track.id)
            }.reversed()

            if (sortedTracks.isEmpty()) {
                emit(null)
            } else {
                emit(convertFromTrackInPlaylistEntityToTrack(sortedTracks))
            }
        }
    }


    override fun getAllFavouritePlaylists(): Flow<List<Playlist>> = flow {
        val playlists = appDatabase.playlistDao().getAllPlaylists()
        emit(convertFromPlaylistEntity(playlists))
    }

    override suspend fun getPlaylistsById(id: Int): Playlist {
        val playlists = appDatabase.playlistDao().getPlaylistsById(id)
        return convertFromPlaylistEntityOnePlaylist(playlists)
    }

    override suspend fun createNewPlaylist(playlist: Playlist) {
        val playlistEntity = convertFromPlaylistToEntity(playlist)
        appDatabase.playlistDao().createPlaylist(playlistEntity)
    }

    override suspend fun deleteNewPlaylist(playlistId: Int) {
        appDatabase.playlistDao().deletePlaylistById(playlistId)
    }

    override suspend fun updatePlaylist(playlist: Playlist) {
        val playlistEntity = convertFromPlaylistToEntity(playlist)
        appDatabase.playlistDao().updatePlaylist(playlistEntity)
    }

    override suspend fun addTrackToPlaylist(playlistId: Int, trackId: String) {
        appDatabase.playlistDao().addTrackToPlaylist(playlistId, trackId)
    }

    override suspend fun insertTrackDetailIntoPlaylistInfo(track: Track) {
        val trackEntity = convertFromTrackToTrackEntity(track)
        appDatabase.trackInPlaylistDao().insertTrack(trackEntity)
    }

    override suspend fun deleteTrackFromPlayList(
        updatedListOfTracks: List<Int>?,
        playlistId: Int,
        idTrackToDelete: Int
    ) {
        val updatedListOfTrackString = updatedListOfTracks?.joinToString(separator = ",")
        appDatabase.playlistDao().deleteTrackFromPlaylist(updatedListOfTrackString, playlistId)
    }

    override suspend fun updateDbListOfTracksInAllPlaylists(id: Int, idTrackToDelete: Int) {
        val playlists = appDatabase.playlistDao().getAllPlaylistsExceptOne(id)

        val hasTrack = playlists.any { playlist ->
            val trackIdsAsStrings = playlist.idOfTracks?.split(",")

            val trackIdsAsInts = trackIdsAsStrings?.mapNotNull {
                val trimmed = it.trim()
                if (trimmed.isNotEmpty()) {
                    trimmed.toInt()
                } else {
                    null
                }
            }

            trackIdsAsInts?.contains(idTrackToDelete) ?: false
        }

        if (!hasTrack) {
            appDatabase.trackInPlaylistDao()
                .deleteTrackByIdFromListOfTracksInPlaylists(idTrackToDelete)
        }
    }

    private fun convertFromPlaylistEntity(playlists: List<PlaylistEntity>): List<Playlist> {
        return playlists.map { playlist -> playlistDbConvertor.map(playlist) }
    }

    private fun convertFromPlaylistEntityOnePlaylist(playlist: PlaylistEntity): Playlist {
        return playlistDbConvertor.map(playlist)
    }


    private fun convertFromPlaylistToEntity(playlist: Playlist): PlaylistEntity {
        return playlistDbConvertor.map(playlist)
    }

    private fun convertFromTrackToTrackEntity(track: Track): TrackInPlaylistsEntity {
        return trackInPlaylistsEntityDbConvertor.map(track)
    }

    private fun convertFromTrackInPlaylistEntityToTrack(tracks: List<TrackInPlaylistsEntity>): List<Track> {
        return tracks.map { track -> trackInPlaylistsEntityDbConvertor.map(track) }
    }
}