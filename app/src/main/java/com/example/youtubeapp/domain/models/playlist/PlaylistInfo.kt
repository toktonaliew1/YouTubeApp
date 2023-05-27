package com.example.youtubeapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.youtubeapp.converter.ContentDetailsConverter
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
        @SerializedName("playlistId")
        var playlistId: String? = null,
        @TypeConverters(ContentDetailsConverter::class)
        @SerializedName("contentDetails")
        var contentDetails: ContentDetails? = null,
        @SerializedName("startTime")
        var startTime: Float = 0f
) : Serializable

data class ContentDetails(
        @SerializedName("videoId")
        var videoId: String? = null,
        @SerializedName("itemCount")
        var itemCount: Int? = null
) : Serializable

data class Snippet(
        @SerializedName("title")
        var title: String? = null,
        @SerializedName("thumbnails")
        var thumbnails: Thumbnails? = null,
        @SerializedName("channelTitle")
        var channelTitle: String? = null,
        @SerializedName("description")
        var description: String? = null,
        @SerializedName("publishedAt")
        var publishedAt: String? = null,
): Serializable


data class Medium(@SerializedName("url")
        var url: String? = null
): Serializable


data class ResourceId(
        @SerializedName("id")
        var videoId: String? = null
) : Serializable

data class Thumbnails(
        @SerializedName("medium")
        var medium: Medium? = null
):Serializable