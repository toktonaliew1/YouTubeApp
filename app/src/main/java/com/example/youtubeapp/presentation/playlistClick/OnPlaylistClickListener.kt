package com.example.youtubeapp.presentation.playlistClick


import com.example.youtubeapp.domain.models.PlaylistItem

interface OnPlaylistClickListener {


    fun onClickItem(item : PlaylistItem)
    fun onClick(item : PlaylistItem)



}