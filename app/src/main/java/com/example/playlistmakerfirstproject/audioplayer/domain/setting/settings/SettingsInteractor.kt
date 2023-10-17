package com.example.playlistmakerfirstproject.audioplayer.domain.setting.settings

import com.example.playlistmakerfirstproject.audioplayer.domain.setting.settings.model.ThemeSettings


interface SettingsInteractor {
    fun getThemeSettings(): ThemeSettings
    fun updateThemeSetting(settings: ThemeSettings)
}