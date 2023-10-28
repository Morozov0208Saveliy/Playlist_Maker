package com.example.playlistmakerfirstproject.audioplayer.data.setting.sharing

import com.example.playlistmakerfirstproject.audioplayer.domain.setting.sharing.model.EmailData


interface ExternalNavigator {

    fun shareLink(link: String)

    fun openLink(link: String)

    fun openEmail(emailToSupport: EmailData)
    fun shareTracks(text: String)
}