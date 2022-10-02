package com.example.youtubeapp.converter

import androidx.room.TypeConverter

import com.example.youtubeapp.domain.models.DetailVideo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConverterVideo {

    @TypeConverter
    fun toJsonVideo(resultModels: MutableList<DetailVideo>?):String?{
        if (resultModels == null) return null
        val gson = Gson()
        val type =
            object : TypeToken<MutableList<DetailVideo>?>() {}.type
        return gson.toJson(resultModels, type)
    }
    @TypeConverter
    fun fromJsonVideo(json: String?): MutableList<DetailVideo>? {
        if (json == null) return null
        val gson = Gson()
        val type =
            object : TypeToken<MutableList<DetailVideo>?>() {}.type
        return gson.fromJson(json, type)
    }
}