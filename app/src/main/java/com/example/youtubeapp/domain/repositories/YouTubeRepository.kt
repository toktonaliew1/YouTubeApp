package com.example.cleanarchicture.domain.repositories

import com.example.youtubeapp.domain.either.Either
import com.example.youtubeapp.domain.models.PlaylistInfo

import kotlinx.coroutines.flow.Flow

interface YouTubeRepository {

    fun getPlayList() : Flow<Either<String, PlaylistInfo>>

}