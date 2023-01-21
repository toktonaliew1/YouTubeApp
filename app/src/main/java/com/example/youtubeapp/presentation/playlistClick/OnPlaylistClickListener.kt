package com.example.youtubeapp.presentation.playlistClick


import com.example.youtubeapp.domain.models.PlaylistItem

interface OnPlaylistClickListener {

    fun onClick(item : PlaylistItem)

}