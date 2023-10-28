package com.example.playlistmakerfirstproject.audioplayer.di

import com.example.playlistmakerfirstproject.audioplayer.domain.api.TrackInteractor
import com.example.playlistmakerfirstproject.audioplayer.domain.favourite.FavouriteInteractor
import com.example.playlistmakerfirstproject.audioplayer.domain.history.HistoryInteractor
import com.example.playlistmakerfirstproject.audioplayer.domain.history.impl.HistoryInteractorImpl
import com.example.playlistmakerfirstproject.audioplayer.domain.favourite.impl.FavouriteInteractorImpl
import com.example.playlistmakerfirstproject.audioplayer.domain.api.impl.TrackInteractorImpl
import com.example.playlistmakerfirstproject.audioplayer.domain.main_navigation.InternalNavigationInteractor
import com.example.playlistmakerfirstproject.audioplayer.domain.main_navigation.impl.InternalNavigationInteractorImpl

import com.example.playlistmakerfirstproject.audioplayer.domain.player.AudioPlayerInteractor
import com.example.playlistmakerfirstproject.audioplayer.domain.player.impl.AudioPlayerInteractorImpl
import com.example.playlistmakerfirstproject.audioplayer.domain.playlists.PlaylistInteractor
import com.example.playlistmakerfirstproject.audioplayer.domain.playlists.impl.PlaylistInteractorImpl
import com.example.playlistmakerfirstproject.audioplayer.domain.setting.SharingInteractor
import com.example.playlistmakerfirstproject.audioplayer.domain.setting.settings.SettingsInteractor
import com.example.playlistmakerfirstproject.audioplayer.domain.setting.settings.impl.SettingsInteractorImpl
import com.example.playlistmakerfirstproject.audioplayer.domain.setting.sharing.impl.SharingInteractorImpl

import com.google.gson.Gson
import org.koin.dsl.module

val interactorModule = module {

    single<FavouriteInteractor> {
        FavouriteInteractorImpl(get())
    }

    single<PlaylistInteractor> {
        PlaylistInteractorImpl(get())
    }

    single<TrackInteractor> {
        TrackInteractorImpl(get())
    }

    single<HistoryInteractor> {
        HistoryInteractorImpl(get(), get())
    }

    single<InternalNavigationInteractor> {
        InternalNavigationInteractorImpl(get())
    }

    single<AudioPlayerInteractor> {
        AudioPlayerInteractorImpl(get())
    }

    single<SettingsInteractor> {
        SettingsInteractorImpl(get())
    }

    single<SharingInteractor> {
        SharingInteractorImpl(get())
    }

    factory { Gson() }


}