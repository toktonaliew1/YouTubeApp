package com.example.youtubeapp.presentation.ui.fragments.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.youtubeapp.domain.models.PlaylistInfo
import com.example.youtubeapp.data.remote.network.Resource
import com.example.youtubeapp.data.repository.YoutubeRepository


class DetailsViewModel (private var repository : YoutubeRepository) : ViewModel() {

    fun getVideoListFromPlaylist(videoListId : String) : LiveData<Resource<PlaylistInfo>>{
        return repository.getVideoListFromPlaylist(videoListId)
    }

    fun getNextVideoListFromPlaylist(videoListId : String,nextPageToken : String): LiveData<Resource<PlaylistInfo>>{
        return repository.getNextVideoListFromPlaylist(nextPageToken,videoListId)
    }

}