package com.example.youtubeapp.presentation.ui.fragments.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchicture.domain.usecases.YouTubeUseCase
import com.example.cleanarchicture.presentation.state.UIState


import com.example.youtubeapp.domain.models.PlaylistItem
import com.example.youtubeapp.data.remote.network.Resource
import com.example.youtubeapp.data.repository.YoutubeRepositoryImpl
import com.example.youtubeapp.domain.either.Either

import com.example.youtubeapp.domain.models.PlaylistInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class PlaylistViewModel(private var repository : YoutubeRepositoryImpl) : ViewModel() {


    private val _youtubeState = MutableStateFlow<UIState<PlaylistInfo>>(UIState.Loading())
    val youtubeState = _youtubeState.asStateFlow()

    var localData = MutableLiveData<MutableList<PlaylistItem>>()




    fun getPlaylists(): LiveData<Resource<PlaylistInfo>> {
        return repository.getPlaylists()
    }

    fun getNextPlaylist(nextPageToken : String): LiveData<Resource<PlaylistInfo>> {
        return repository.getNextPlaylists(nextPageToken)
    }

    fun getPlaylistFromLD(){
        localData.value = repository.getPlaylist()
    }

    fun addPlaylistsToLD(list : MutableList<PlaylistItem>){
        repository.addPlaylists(list)
    }

}