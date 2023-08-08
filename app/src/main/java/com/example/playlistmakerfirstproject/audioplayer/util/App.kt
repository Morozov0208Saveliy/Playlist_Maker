package com.example.playlistmakerfirstproject.audioplayer.util

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.playlistmakerfirstproject.audioplayer.di.dataModule
import com.example.playlistmakerfirstproject.audioplayer.di.interactorModule
import com.example.playlistmakerfirstproject.audioplayer.di.repositoryModule
import com.example.playlistmakerfirstproject.audioplayer.di.viewModelModule
import com.example.playlistmakerfirstproject.audioplayer.domain.setting.settings.SettingsInteractor
import com.example.playlistmakerfirstproject.audioplayer.domain.setting.settings.model.ThemeSettings
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.java.KoinJavaComponent.getKoin

class App (): Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(dataModule, interactorModule, repositoryModule, viewModelModule)
        }

        val settingsInteractor: SettingsInteractor = getKoin().get()
        darkMode(settingsInteractor.getThemeSettings())

    }

    // устанавливаем dark Mode
    private fun darkMode(isDarkMode: ThemeSettings) {
        if (isDarkMode == ThemeSettings.MODE_DARK_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

}