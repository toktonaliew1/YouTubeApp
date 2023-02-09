package com.example.youtubeapp.data.remote.network.apiservisec


import com.example.youtubeapp.domain.models.PlaylistInfo
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApi {

    @GET("youtube/v3/playlists")
    suspend fun getPlaylists(
        @Query("part")part : String,
        @Query("channelId") channelId:String,
        @Query("key") key : String,
        @Query("maxResults") maxResults : Int
    ):PlaylistInfo

    @GET("youtube/v3/playlists")
    suspend fun getNextPlaylists(
        @Query("part")part : String,
        @Query("channelId") channelId:String,
        @Query("key") key : String,
        @Query("maxResults") maxResults : Int,
        @Query("pageToken") nextPageToken : String? = null
    ):PlaylistInfo

    @GET("youtube/v3/playlistItems")
    suspend fun getVideoListFromPlaylist(
        @Query("part")part : String,
        @Query("playlistId") playlistId:String,
        @Query("key") key : String,
        @Query("maxResults") maxResults : Int
    ) : PlaylistInfo

    @GET("youtube/v3/playlistItems")
    suspend fun getNextVideoListFromPlaylist(
        @Query("part")part : String,
        @Query("playlistId") playlistId:String,
        @Query("key") key : String,
        @Query("maxResults") maxResults : Int,
        @Query("pageToken") nextPageToken : String? = null
    ) : PlaylistInfo


    @GET("youtube/v3/playlistItems")
    suspend fun getPlaylistItems(
        @Query("part") part: String,
        @Query("playlistId") playlist: String,
        @Query("videoId") videoId: String,
        @Query("key") key: String
    ) : PlaylistInfo


    companion object{
        fun provideYoutubeApi(retrofit: Retrofit) : YouTubeApi{
            return retrofit.create(YouTubeApi::class.java)
        }
    }
}