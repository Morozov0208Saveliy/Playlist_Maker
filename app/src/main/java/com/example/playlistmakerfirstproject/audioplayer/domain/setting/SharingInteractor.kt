package com.example.playlistmakerfirstproject.audioplayer.domain.setting

interface SharingInteractor {
    fun shareApp()
    fun openTerms()
    fun openSupport(subject:String, text:String)
    fun shareTracks(text: String)

}