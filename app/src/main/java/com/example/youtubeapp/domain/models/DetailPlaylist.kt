package com.example.youtubeapp.domain.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "detailPlaylist")
data class DetailPlayList(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Long? = null,

    var nextPageToken: String? = null,

    var items: MutableList<DetailVideo>? = null
):Serializable

data class DetailVideo(
    var id: String? = null,
    var snippet: Snippet? = null
) : Serializable

data class ResourceId(
    var videoId: String? = null
) : Serializable
