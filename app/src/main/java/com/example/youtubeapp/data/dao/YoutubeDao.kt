package com.example.youtubeapp.data.dao

import androidx.room.*
import com.example.youtubeapp.domain.models.*

@Dao
interface YoutubeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPlaylist(list: MutableList<PlaylistItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addVideos(list: MutableList<PlaylistItem>)

    @Query("SELECT * FROM PlaylistItem")
    fun getPlaylist() : MutableList<PlaylistItem>

    @Transaction
    @Query("SELECT * FROM PlaylistItem WHERE PlaylistItem.id =:id")
    fun getAllVideos(id : String): DetailPlayList?
}