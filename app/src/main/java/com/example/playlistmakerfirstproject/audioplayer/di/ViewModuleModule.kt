package com.example.playlistmakerfirstproject.audioplayer.di

import androidx.appcompat.app.AppCompatActivity
import com.example.playlistmakerfirstproject.audioplayer.presentation.audioPlayer.AudioPlayerViewModel
import com.example.playlistmakerfirstproject.audioplayer.presentation.library.playlists.FavPlaylistFragmentViewModel
import com.example.playlistmakerfirstproject.audioplayer.presentation.library.tracks.FavTracksFragmentViewModel
import com.example.playlistmakerfirstproject.audioplayer.presentation.playlistDetails.PlaylistDetailsFragmentViewModel
import com.example.playlistmakerfirstproject.audioplayer.presentation.playlistsCreation.PlaylistCreationViewModel
import com.example.playlistmakerfirstproject.audioplayer.presentation.search.SearchViewModel
import com.example.playlistmakerfirstproject.audioplayer.presentation.settings.SettingViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {


    viewModel {
        AudioPlayerViewModel(get(), get(), get())
    }

    viewModel { (activity: AppCompatActivity) ->
        PlaylistCreationViewModel(androidContext(), get())
    }

    viewModel {
        SettingViewModel(get(), get())
    }

    viewModel {
        SearchViewModel(get(), get(), get())
    }

    viewModel {
        FavPlaylistFragmentViewModel(get())
    }

    viewModel {
        PlaylistDetailsFragmentViewModel(get(), get())
    }

    viewModel {
        FavTracksFragmentViewModel(androidContext(), get())
    }


}