package com.example.playlistmakerfirstproject.audioplayer.presentation.library.tracks

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playlistmakerfirstproject.R
import com.example.playlistmakerfirstproject.audioplayer.domain.favourite.FavouriteInteractor
import com.example.playlistmakerfirstproject.audioplayer.domain.models.Track
import com.example.playlistmakerfirstproject.audioplayer.presentation.ui.FavoriteState
import kotlinx.coroutines.launch


class FavTracksFragmentViewModel(
    private val context: Context,
    private val favouriteInteractor: FavouriteInteractor.FavouriteInteractor,
) : ViewModel() {

    private val stateLiveData = MutableLiveData<FavoriteState>()
    fun observeState(): LiveData<FavoriteState> = stateLiveData

    fun fillData() {
        renderState(FavoriteState.Loading)
        viewModelScope.launch {
            favouriteInteractor.getAllFavouriteTracks()
                .collect { tracks ->
                    processResult(tracks)
                }
        }
    }

    private fun processResult(tracks: List<Track>) {
        if (tracks.isEmpty()) {
            renderState(FavoriteState.Empty(context.getString(R.string.noFavTracksFound)))
        } else {
            for (track in tracks) {
                track.isFavorite = true
            }
            renderState(FavoriteState.Content(tracks))
        }
    }

    private fun renderState(state: FavoriteState) {
        stateLiveData.postValue(state)
    }


}
