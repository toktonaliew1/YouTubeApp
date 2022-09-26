package com.example.youtubeapp.data.repository

import androidx.lifecycle.liveData
import com.example.youtubeapp.data.dao.YoutubeDao
import com.example.youtubeapp.data.dao.YoutubeDataBase
import com.example.youtubeapp.domain.models.PlaylistItem
import com.example.youtubeapp.domain.models.VideoListItem
import com.example.youtubeapp.data.remote.network.Resource
import com.example.youtubeapp.data.remote.network.apiservisec.YouTubeApi
import kotlinx.coroutines.Dispatchers

class YoutubeRepository(private var api: YouTubeApi, private var database: YoutubeDataBase
, private var youtubeDao: YoutubeDao) {

    companion object {
        const val YOUTUBE_API_KEY = "AIzaSyAcCV7Vqi1KRKr6ZKwms-Hd8Omi6aWCHps"
    }

    val channelId = "UCKsqMPIIhev3qbMxCL8Emvw"
    val part = "snippet,contentDetails"
    val maxResults = 30


    fun getPlaylist() : MutableList<PlaylistItem>{
        return database.wordDao().getPlaylist()
    }

    fun getAllVideos(id : String) : VideoListItem?{
        return database.wordDao().getAllVideos(id)
    }

    fun addPlaylists(list : MutableList<PlaylistItem>) {
        database.wordDao().addPlaylist(list)
    }

    fun addVideos(list : MutableList<PlaylistItem>){
        database.wordDao().addVideos(list)
    }



    fun getPlaylists() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = api.getPlaylists(part, channelId, YOUTUBE_API_KEY,maxResults)))
        }catch (e : Exception){
            emit(Resource.error(data = null,message = e.message.toString()))
        }
    }


    fun fetchPlaylists() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        emit(Resource.fetchFromDB(youtubeDao.getPlaylists()))
        try {
            val request = api.getPlayList(part, channelId, YOUTUBE_API_KEY, maxResults)
            youtubeDao.insertPlaylist(request)
            emit(Resource.success(data = request))
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            emit(Resource.error(data = null, message = e.message ?: "Error"))
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

}