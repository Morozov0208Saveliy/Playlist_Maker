package com.example.playlistmakerfirstproject.audioplayer.data.network


import com.example.playlistmakerfirstproject.audioplayer.data.dto.TrackSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Itunes {
    @GET("/search?entity=song")
    fun search(@Query("term") text: String) : Call<TrackSearchResponse>
}