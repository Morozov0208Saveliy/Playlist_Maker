package com.example.playlistmakerfirstproject.audioplayer.di

import com.example.playlistmakerfirstproject.audioplayer.data.db.convertors.PlaylistDbConvertor
import com.example.playlistmakerfirstproject.audioplayer.data.db.convertors.TrackConvertor
import com.example.playlistmakerfirstproject.audioplayer.data.network.TrackRepositoryImpl
import com.example.playlistmakerfirstproject.audioplayer.data.db.convertors.TrackInPlaylistsEntityDbConvertor
import com.example.playlistmakerfirstproject.audioplayer.data.favourites.FavouriteRepositoryImpl
import com.example.playlistmakerfirstproject.audioplayer.data.history.HistoryRepository
import com.example.playlistmakerfirstproject.audioplayer.data.history.impl.HistoryRepositoryImpl
import com.example.playlistmakerfirstproject.audioplayer.data.m_navigation.InternalNavigationRepository
import com.example.playlistmakerfirstproject.audioplayer.data.m_navigation.impl.InternalNavigationRepositoryImpl
import com.example.playlistmakerfirstproject.audioplayer.data.player.MediaPlayerRepositoryImpl
import com.example.playlistmakerfirstproject.audioplayer.data.playlists.PlaylistRepositoryImpl
import com.example.playlistmakerfirstproject.audioplayer.data.setting.settings.SettingsRepository
import com.example.playlistmakerfirstproject.audioplayer.data.setting.settings.impl.SettingsRepositoryImpl
import com.example.playlistmakerfirstproject.audioplayer.data.setting.sharing.ExternalNavigator
import com.example.playlistmakerfirstproject.audioplayer.data.setting.sharing.impl.ExternalNavigationImpl
import com.example.playlistmakerfirstproject.audioplayer.domain.api.TrackRepository
import com.example.playlistmakerfirstproject.audioplayer.domain.favourite.FavouriteRepository
import com.example.playlistmakerfirstproject.audioplayer.domain.player.MediaPlayerRepository
import com.example.playlistmakerfirstproject.audioplayer.domain.playlists.PlaylistRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<FavouriteRepository> {
        FavouriteRepositoryImpl(get(), get())
    }
    single<PlaylistRepository> {
        PlaylistRepositoryImpl(get(), get(), get(),get())
    }

    factory { TrackConvertor() }

    factory { PlaylistDbConvertor() }

    factory { TrackInPlaylistsEntityDbConvertor() }


    single<TrackRepository> {
        TrackRepositoryImpl(get(),get())
    }

    single<InternalNavigationRepository> {
        InternalNavigationRepositoryImpl(androidContext())
    }

    single<MediaPlayerRepository> {
        MediaPlayerRepositoryImpl(get(),get())
    }

    single<ExternalNavigator> {
        ExternalNavigationImpl(androidContext())
    }

    single<SettingsRepository> {
        SettingsRepositoryImpl(get(named(SHARED_PREFS_DARK_MODE)))
    }

    single<HistoryRepository> {
        HistoryRepositoryImpl(get(named(SHARED_PREFS_SEARCH_HISTORY)),get())
    }

}