package com.example.youtubeapp.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.youtubeapp.domain.models.ContentDetails

class ContentDetailsConverter {

    @TypeConverter
    fun fromRaw(raw: String?): ContentDetails? {
        if (raw == null) return null
        val gson = Gson()
        val type = object : TypeToken<ContentDetails?>() {}.type
        return gson.fromJson<ContentDetails>(raw, type)
    }

    @TypeConverter
    fun toRaw(contentDetails: ContentDetails?): String? {
        if (contentDetails == null) return null
        val gson = Gson()
        val type = object : TypeToken<ContentDetails?>() {}.type
        return gson.toJson(contentDetails, type)
    }

}