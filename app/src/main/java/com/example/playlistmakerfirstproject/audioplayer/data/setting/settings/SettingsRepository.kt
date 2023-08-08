package com.example.playlistmakerfirstproject.audioplayer.data.setting.settings

import com.example.playlistmakerfirstproject.audioplayer.domain.setting.settings.model.ThemeSettings

interface SettingsRepository {

    fun getThemeSettings(): ThemeSettings
    fun updateThemeSetting(settings: ThemeSettings)
}