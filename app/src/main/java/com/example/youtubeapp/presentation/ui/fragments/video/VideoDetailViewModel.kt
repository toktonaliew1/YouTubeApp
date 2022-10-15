package com.example.youtubeapp.presentation.ui.fragments.video

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.youtubeapp.base.BaseViewModel
import com.example.youtubeapp.data.remote.network.Status
import com.example.youtubeapp.data.repository.YoutubeRepository
import com.example.youtubeapp.domain.models.PlaylistInfo
import com.example.youtubeapp.domain.models.PlaylistItem
import com.example.youtubeapp.domain.models.PlaylistItems

class VideoDetailViewModel(private val repository: YoutubeRepository) : BaseViewModel(){
    var playlists = MutableLiveData<MutableList<PlaylistItems>>()

//    init {
//        fetchPlaylists()
//    }

//    private fun fetchPlaylists() {
//        repository.fetchPlaylists().observeForever {
//            when (it.status) {
//                Status.SUCCESS -> playlists.postValue(it.data?.items!!)
//                Status.ERROR -> errorMessage.postValue(it.message.toString())
//            }
//        }
//    }
}