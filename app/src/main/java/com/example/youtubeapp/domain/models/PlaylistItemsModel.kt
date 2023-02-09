package com.example.youtubeapp.domain.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

data class PlaylistItemsModel(

    @SerializedName("items")
    val items: List<PlaylistInfo>
)
