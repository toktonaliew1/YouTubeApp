package com.example.youtubeapp.presentation.ui.fragments.video

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.youtubeapp.domain.models.PlaylistInfo
import com.example.youtubeapp.domain.models.PlaylistItem

class VideoDetailViewModel : ViewModel() {

    val currentVideo = MutableLiveData<PlaylistItem>()
    var firsVideoId: String? = null
    var detailsPlaylist = MutableLiveData<PlaylistInfo>()

    fun setUpPlaylistData(detailsPlaylist: PlaylistInfo) {
        if (this.detailsPlaylist.value == null) {
            if (detailsPlaylist.items!!.isNotEmpty()) currentVideo.value = detailsPlaylist.items!![0]
            this.detailsPlaylist.value = detailsPlaylist
        }
    }

    fun setUpFirstVideoId(videoId: String?) {
        if (firsVideoId == null)
            firsVideoId = videoId
    }

    fun changeVideo(detailItem: PlaylistItem) {
        currentVideo.value = detailItem
    }

    fun changeLastSecond(time: Float) {
        currentVideo.value?.let {
            it.startTime = time
        }
    }

}