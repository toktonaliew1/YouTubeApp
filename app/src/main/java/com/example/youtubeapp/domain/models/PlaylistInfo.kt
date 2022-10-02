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

@Entity(tableName = "playlist")
data class PlaylistInfo(
        @PrimaryKey(autoGenerate = true)
        @SerializedName("idRoom")
        var id: Int? = null,
        @SerializedName("id")
        var apiId: String? = null,
        var kind: String? = null,
        var etag: String? = null,
        var nextPageToken: String? = null,
        var items: MutableList<PlaylistItems>? = null
)

data class Playlist(
        var nextPageToken: String? = null,
        var items: MutableList<PlaylistItem>? = null
): Serializable


@Entity
data class PlaylistItem(
        @PrimaryKey
        var id: String,
        @TypeConverters(SnippetConverter::class)
        var snippet: Snippet? = null,
        var playlistId: String? = null,
        @TypeConverters(ContentDetailsConverter::class)
        var contentDetails: ContentDetails? = null,
        var startTime: Float = 0f
) : Serializable



data class PlaylistItems(
        @SerializedName("id")
        var apiId: String? = null,
        var kind: String? = null,
        var etag: String? = null,
        var snippet: Snippet? = null,
        var contentDetails: ContentDetails? = null,
        var items: MutableList<PlaylistItems>? = null,
        var nextPageToken: String? = null
):Serializable



data class ContentDetails(
        var videoId: String? = null,
        var itemCount: Int? = null
) : Serializable

data class Snippet(
        var title: String? = null,
        @TypeConverters(ImageInfoConverter::class)
        var thumbnails: ImageInfo? = null,
        var channelTitle: String? = null,
        var resourceId: ResourceId?=null,
        var description: String? = null
): Serializable

data class ImageInfo(
        @TypeConverters(MediumConverter::class)
        var medium: Medium? = null
): Serializable

data class Medium(
        var url: String? = null
): Serializable

