package com.example.youtubeapp.domain.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.youtubeapp.converter.ContentDetailsConverter
import com.example.youtubeapp.converter.ImageInfoConverter
import com.example.youtubeapp.converter.MediumConverter
import com.example.youtubeapp.converter.SnippetConverter
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PlaylistInfo(
        var nextPageToken: String? = null,
        var items: MutableList<PlaylistItem>? = null
): Serializable

@Entity
data class PlaylistItem(
        @PrimaryKey
        @SerializedName("id")
        var id: String,
        @TypeConverters(SnippetConverter::class)
        @SerializedName("snippet")
        var snippet: Snippet? = null,
        var playlistId: String? = null,
        @TypeConverters(ContentDetailsConverter::class)
        @SerializedName("contentDetails")
        var contentDetails: ContentDetails? = null,
        var startTime: Float = 0f
) : Serializable

data class ContentDetails(
        var videoId: String? = null,
        var itemCount: Int? = null
) : Serializable

data class Snippet(
        var title: String? = null,
        @TypeConverters(ImageInfoConverter::class)
        var thumbnails: ImageInfo? = null,
        var channelTitle: String? = null,
        var description: String? = null,
        var publishedAt: String? = null,
): Serializable

data class Snippett(
        var publishedAt: String? = null,
        var channelId: String? = null,
        var title: String? = null,
        var description: String? = null,
        var thumbnails: Thumbnails? = null,
        var resourceId: ResourceId?=null,
        var playlistId: String? = null
):Serializable

data class ImageInfo(
        @TypeConverters(MediumConverter::class)
        var medium: Medium? = null
): Serializable

data class Medium(
        var url: String? = null
): Serializable

data class DetailVideo(
        var id: String? = null,
        var snippet: Snippett? = null
) : Serializable

data class ResourceId(
        var videoId: String? = null
) : Serializable

data class Thumbnails(
        var medium: Medium? = null
):Serializable