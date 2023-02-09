package com.example.youtubeapp.presentation.ui.fragments.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.youtubeapp.data.remote.network.Resource
import com.example.youtubeapp.data.repository.YoutubeRepositoryImpl
import com.example.youtubeapp.domain.models.PlaylistInfo

class VideoDetailViewModel(val repository: YoutubeRepositoryImpl) : ViewModel() {
    private val _playlistItem = MutableLiveData<PlaylistInfo?>()
    val playlistItem = _playlistItem
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading
    private val _isAllItemLoaded = MutableLiveData<Boolean>()
    val isAllItemLoaded = _isAllItemLoaded
    var nextPageToken: String? = null

    fun getPlaylistItem(playlistId: String, videoId: String): LiveData<Resource<PlaylistInfo>> {
        return repository.getPlaylistItems(playlistId, videoId)
    }
}