package com.example.playlistmakerfirstproject.audioplayer.di

import com.example.playlistmaker.presentation.library.FavPlaylistFragmentViewModel
import com.example.playlistmakerfirstproject.audioplayer.presentation.audioPlayer.AudioPlayerViewModel
import com.example.playlistmakerfirstproject.audioplayer.presentation.library.FavTracksFragmentViewModel
import com.example.playlistmakerfirstproject.audioplayer.presentation.search.SearchViewModel
import com.example.playlistmakerfirstproject.audioplayer.presentation.settings.SettingViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {


    viewModel {
        AudioPlayerViewModel(get(), get())
    }

    viewModel {
        SettingViewModel(get(), get())
    }

    viewModel {
        SearchViewModel(get(), get(), get())
    }

    viewModel {
        FavPlaylistFragmentViewModel()
    }

    viewModel {
        FavTracksFragmentViewModel(androidContext(), get())
    }


}