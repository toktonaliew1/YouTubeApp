package com.example.youtubeapp.presentation.ui.fragments.video

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.youtubeapp.data.remote.network.ApiConfig
import com.example.youtubeapp.data.remote.network.Resource
import com.example.youtubeapp.data.repository.YoutubeRepository
import com.example.youtubeapp.domain.models.PlaylistInfo
import com.example.youtubeapp.domain.models.PlaylistItem
import com.example.youtubeapp.domain.models.it.PlaylistItemsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideoDetailViewModel(val repository: YoutubeRepository) : ViewModel() {
    private val _playlistItem = MutableLiveData<PlaylistItemsModel?>()
    val playlistItem = _playlistItem
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading
    private val _isAllItemLoaded = MutableLiveData<Boolean>()
    val isAllItemLoaded = _isAllItemLoaded
    var nextPageToken: String? = null

    fun getPlaylistItem(playlistId: String, videoId: String): LiveData<Resource<PlaylistItemsModel>> {
        return repository.getPlaylistItems(playlistId, videoId)
    }




    companion object {
        private val TAG = VideoDetailViewModel::class.java.simpleName
    }

}