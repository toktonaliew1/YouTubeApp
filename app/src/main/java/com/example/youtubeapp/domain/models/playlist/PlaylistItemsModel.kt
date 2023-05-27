package com.example.youtubeapp.domain.models.playlist

import com.example.youtubeapp.domain.models.PlaylistInfo
import com.google.gson.annotations.SerializedName

data class PlaylistItemsModel(

    @SerializedName("items")
    val items: List<PlaylistInfo>
)
