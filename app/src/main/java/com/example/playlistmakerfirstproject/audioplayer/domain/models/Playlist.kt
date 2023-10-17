package com.example.playlistmakerfirstproject.audioplayer.domain.models

import java.io.Serializable

data class Playlist(
    val id: Int,
    var name: String,
    var details: String?,
    var imagePath: String?,
    var idOfTracks: List<Int>?,
    var numberOfTracks: Int? =  idOfTracks?.size ?:0

): Serializable
