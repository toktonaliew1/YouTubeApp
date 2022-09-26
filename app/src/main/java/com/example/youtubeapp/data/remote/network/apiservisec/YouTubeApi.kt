package com.example.youtubeapp.data.remote.network.apiservisec

import com.example.youtubeapp.domain.models.Playlist
import com.example.youtubeapp.domain.models.PlaylistInfo
import com.example.youtubeapp.domain.models.PlaylistItem
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApi {

    @GET("playlists")
    suspend fun getPlaylists(
        @Query("part")part : String,
        @Query("channelId") channelId:String,
        @Query("key") key : String,
        @Query("maxResults") maxResults : Int
    ): Playlist

    @GET("playlists")
    suspend fun getPlayList(
        @Query("part")part : String,
        @Query("channelId") channelId:String,
        @Query("key") key : String,
        @Query("maxResults") maxResults : Int
    ): PlaylistInfo

    @GET("playlists")
    suspend fun getNextPlaylists(
        @Query("part")part : String,
        @Query("channelId") channelId:String,
        @Query("key") key : String,
        @Query("maxResults") maxResults : Int,
        @Query("pageToken") nextPageToken : String? = null
    ): Playlist

    @GET("playlistItems")
    suspend fun getVideoListFromPlaylist(
        @Query("part")part : String,
        @Query("playlistId") playlistId:String,
        @Query("key") key : String,
        @Query("maxResults") maxResults : Int
    ) : Playlist

    @GET("playlistItems")
    suspend fun getNextVideoListFromPlaylist(
        @Query("part")part : String,
        @Query("playlistId") playlistId:String,
        @Query("key") key : String,
        @Query("maxResults") maxResults : Int,
        @Query("pageToken") nextPageToken : String? = null
    ) : Playlist


    companion object{
        fun provideYoutubeApi(retrofit: Retrofit) : YouTubeApi {
                return retrofit.create(YouTubeApi::class.java)
        }
    }
}