package com.example.playlistmakerfirstproject.audioplayer.domain.setting.sharing

interface SharingInteractor {
    fun shareApp()
    fun openTerms()
    fun openSupport(subject:String, text:String)
}