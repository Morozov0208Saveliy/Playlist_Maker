package com.example.playlistmakerfirstproject.audioplayer.presentation.ui.settings

import com.example.playlistmakerfirstproject.audioplayer.presentation.ui.settings.model.ThemeSettings

interface SettingsInteractor {
    fun getThemeSettings(): ThemeSettings
    fun updateThemeSetting(settings: ThemeSettings)
}