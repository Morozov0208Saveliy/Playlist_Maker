package com.example.playlistmakerfirstproject.audioplayer.presentation.playlistDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playlistmakerfirstproject.audioplayer.domain.models.Playlist
import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track
import com.example.playlistmakerfirstproject.audioplayer.domain.playlists.PlaylistInteractor
import com.example.playlistmakerfirstproject.audioplayer.domain.setting.SharingInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlaylistDetailsFragmentViewModel(
    private val playlistInteractor: PlaylistInteractor,
    private val sharingInteractor: SharingInteractor
) : ViewModel() {

    private val _playlistDetails = MutableLiveData<Playlist>()
    val playlistDetails: LiveData<Playlist> get() = _playlistDetails


    private val _tracksLiveData = MutableLiveData<List<Track>?>()
    val tracksLiveData: LiveData<List<Track>?> get() = _tracksLiveData


    fun getPlaylistById(id: Int) {
        viewModelScope.launch {
            val playlist = playlistInteractor.getPlaylistsById(id)
            _playlistDetails.value = playlist
        }
    }

    fun getTracksFromOnePlaylist(playlist: Playlist) {
        val listOfId = playlist.idOfTracks?.toList()
        if (listOfId != null) {
            viewModelScope.launch {
                playlistInteractor.getTracksOnlyFromPlaylist(listOfId)
                    .collect { track ->
                        processResult(track)
                    }
            }
        } else {
            _tracksLiveData.postValue(listOf())
        }
    }


    private fun getTracksFromOnePlaylistUpdated(updatedListOfId: List<Int>?) {
        if (updatedListOfId?.isEmpty() == true || updatedListOfId == null) {
            processResult(emptyList())
        } else {
            viewModelScope.launch {
                playlistInteractor.getTracksOnlyFromPlaylist(updatedListOfId!!)
                    .collect { track ->
                        processResult(track)
                    }
            }
        }
    }

    private fun processResult(tracks: List<Track>?) {
        _tracksLiveData.postValue(tracks)
    }

    suspend fun deleteTrackFromPlaylist(track: Track, playlist: Playlist) {
        val idTrackToDelete = track.trackId
        var listOfIdsInPlaylist = playlist.idOfTracks?.toMutableList()
        listOfIdsInPlaylist?.remove(track.trackId)
        playlist.idOfTracks = listOfIdsInPlaylist?.reversed()
        if (playlist.numberOfTracks != null) {
            playlist.numberOfTracks = playlist.numberOfTracks!! - 1
        }
        _playlistDetails.postValue(playlist)

        playlistInteractor.deleteTrackFromPlaylist(
            listOfIdsInPlaylist?.toList(),
            playlist.id,
            idTrackToDelete
        )

        getTracksFromOnePlaylist(playlist)
        viewModelScope.launch(Dispatchers.IO) {
            updateDbListOfTracksInAllPlaylists(playlist.id, idTrackToDelete)
        }

    }

    private suspend fun updateDbListOfTracksInAllPlaylists(playlistId: Int, idTrackToDelete: Int) {
        playlistInteractor.updateDbListOfTracksInAllPlaylists(playlistId, idTrackToDelete)
    }

    suspend fun deletePlaylistById(playlistId: Int, listOfTracks: ArrayList<Track>) {
        playlistInteractor.deleteNewPlaylist(playlistId)

        viewModelScope.launch(Dispatchers.IO) {
            for (track in listOfTracks) {
                val idTrackToDelete = track.trackId
                updateDbListOfTracksInAllPlaylists(playlistId, idTrackToDelete)
            }
        }
    }

    fun shareTracks(playlist: Playlist, listOfTracks: List<Track>) {
        val message = generateMessage(playlist, listOfTracks)
        sharingInteractor.shareTracks(message)
    }

    private fun generateMessage(playlist: Playlist, tracks: List<Track>): String {
        val sb = StringBuilder()
        sb.append(playlist.name).append("\n")
        if (playlist.details?.isNotEmpty() == true) {
            sb.append(playlist.details).append("\n")
        }
        val trackWord = getTrackWordForm(tracks.size)
        sb.append(tracks.size).append("$trackWord").append("\n")
        tracks.forEachIndexed { index, track ->
            val trackDuration = convertMillisToTimeFormat(track.trackTimeMillis)
            sb.append("${index + 1}. ${track.artistName} - ${track.trackName} ($trackDuration)")
                .append("\n")
        }

        return sb.toString()
    }


    private fun convertMillisToTimeFormat(millis: Int): String {
        val minutes = millis / 1000 / 60
        val seconds = millis / 1000 % 60
        return String.format("%02d:%02d", minutes, seconds)
    }

    private fun getTrackWordForm(count: Int): String {
        return when {
            count % 100 in 11..14 -> "треков"
            count % 10 == 1 -> "трек"
            count % 10 in 2..4 -> "трека"
            else -> "треков"
        }
    }


}