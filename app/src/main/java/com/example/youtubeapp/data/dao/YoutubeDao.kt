package com.example.youtubeapp.data.dao

import androidx.room.*
import com.example.youtubeapp.domain.models.*

@Dao
interface YoutubeDao {

    @Query("SELECT * FROM PlaylistItem")
    fun getPlaylist():MutableList<PlaylistItem>

    @Query("SELECT * FROM playlist")
    suspend fun getPlaylists(): PlaylistInfo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylist(items: PlaylistInfo)

    @Query("SELECT*FROM detailPlaylist")
    suspend fun getDetailPlaylist(): DetailPlayList

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun insertDetailPlaylist(items: DetailPlayList)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPlaylist(list: MutableList<PlaylistItem>)

}