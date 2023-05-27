package com.example.youtubeapp.domain.models.playlist

import androidx.room.Embedded
import androidx.room.Relation
import com.example.youtubeapp.domain.models.PlaylistItem

class DetailPlayList {

    @Embedded
    lateinit var playListItem : PlaylistItem

    @Relation(parentColumn = "id",entityColumn = "playlistId")
    lateinit var videoList : MutableList<PlaylistItem>
}