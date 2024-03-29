package com.example.youtubeapp.data.repository

import androidx.lifecycle.liveData
import com.example.youtubeapp.data.dao.YoutubeDataBase
import com.example.youtubeapp.domain.models.PlaylistItem
import com.example.youtubeapp.data.remote.network.Resource
import com.example.youtubeapp.data.remote.network.apiservisec.YouTubeApi
import kotlinx.coroutines.Dispatchers

class YoutubeRepositoryImpl(private var api: YouTubeApi, private var database: YoutubeDataBase)
{
    companion object {
        const val YOUTUBE_API_KEY = "AIzaSyAcCV7Vqi1KRKr6ZKwms-Hd8Omi6aWCHps"
    }

    val channelId = "UCKsqMPIIhev3qbMxCL8Emvw"
    val part = "snippet,contentDetails"
    val maxResults = 8

    fun getPlaylist() : MutableList<PlaylistItem>{
        return database.wordDao().getPlaylist()
    }

    fun addPlaylists(list : MutableList<PlaylistItem>){
        database.wordDao().addPlaylist(list)
    }

    fun getPlaylists() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = api.getPlaylists(part, channelId, YOUTUBE_API_KEY,maxResults)))
        }catch (e : Exception){
            emit(Resource.error(data = null,message = e.message.toString()))
        }
    }

    fun getNextPlaylists(nextPageToken : String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = api.getNextPlaylists(part, channelId, YOUTUBE_API_KEY,maxResults,nextPageToken)))
        }catch (e : Exception){
            emit(Resource.error(data = null,message = e.message.toString()))
        }
    }

    fun getVideoListFromPlaylist(videoListId : String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = api.getVideoListFromPlaylist(part, videoListId, YOUTUBE_API_KEY,maxResults)))
        }catch (e : Exception){
            emit(Resource.error(data = null,message = e.message.toString()))
        }
    }

    fun getNextVideoListFromPlaylist(nextPageToken : String,videoListId : String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = api.getNextVideoListFromPlaylist(part, videoListId, YOUTUBE_API_KEY,maxResults,nextPageToken)))
        }catch (e : Exception){
            emit(Resource.error(data = null,message = e.message.toString()))
        }
    }


    fun getPlaylistItems(playlistId: String, videoId: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = api.getPlaylistItems(part, playlistId, videoId, YOUTUBE_API_KEY)))
        }catch (e : Exception){
            emit(Resource.error(data = null,message = e.message.toString()))
        }
    }
}