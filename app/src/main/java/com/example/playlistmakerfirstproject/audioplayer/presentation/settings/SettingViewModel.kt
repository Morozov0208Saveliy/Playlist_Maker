package com.example.playlistmakerfirstproject.audioplayer.presentation.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.playlistmakerfirstproject.audioplayer.domain.setting.SharingInteractor
import com.example.playlistmakerfirstproject.audioplayer.domain.setting.settings.SettingsInteractor
import com.example.playlistmakerfirstproject.audioplayer.domain.setting.settings.model.ThemeSettings

class SettingViewModel(
    private val settingsInteractor: SettingsInteractor,
    private val sharingInteractor: SharingInteractor
) : ViewModel() {

    fun isDarkModeOnStart(): Boolean {
        var modeStart = settingsInteractor.getThemeSettings()
        return modeStart == ThemeSettings.MODE_DARK_YES
    }

    private var modeLiveData = MutableLiveData(isDarkModeOnStart())

    fun getModeLiveData(): LiveData<Boolean> = modeLiveData

    init {
        var mode = settingsInteractor.getThemeSettings()
        if (mode == ThemeSettings.MODE_DARK_YES) {
            modeLiveData.postValue(true)
        } else if (mode == ThemeSettings.MODE_DARK_NO) {
            modeLiveData.postValue(false)
        }
    }


    fun changeMode(isDarkMode: Boolean) {
        if (isDarkMode == true) {
            settingsInteractor.updateThemeSetting(ThemeSettings.MODE_DARK_YES)
            modeLiveData.postValue(true)
        } else {
            settingsInteractor.updateThemeSetting(ThemeSettings.MODE_DARK_NO)
            modeLiveData.postValue(false)
        }
    }

    // sharing part
    fun openSupport(subject: String, text: String) {
        sharingInteractor.openSupport(subject = subject, text = text)
    }

    fun shareApp() {
        sharingInteractor.shareApp()
    }

    fun legalAgreement() {
        sharingInteractor.openTerms()
    }

}