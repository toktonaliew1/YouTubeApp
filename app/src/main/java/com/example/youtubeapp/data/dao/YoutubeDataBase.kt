package com.example.youtubeapp.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.youtubeapp.converter.ContentDetailsConverter
import com.example.youtubeapp.converter.ImageInfoConverter
import com.example.youtubeapp.converter.MediumConverter
import com.example.youtubeapp.converter.SnippetConverter
import com.example.youtubeapp.domain.models.PlaylistItem

@Database(entities = [PlaylistItem::class], version = 1, exportSchema = false)
@TypeConverters(
    ContentDetailsConverter::class,
    ImageInfoConverter::class,
    MediumConverter::class,
    SnippetConverter::class
)
abstract class YoutubeDataBase : RoomDatabase() {

    abstract fun wordDao(): YoutubeDao

    companion object {

        @Volatile
        private var INSTANCE: YoutubeDataBase? = null

        fun getDatabase(context: Context): YoutubeDataBase {


            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    YoutubeDataBase::class.java,
                    "word_database"
                )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}