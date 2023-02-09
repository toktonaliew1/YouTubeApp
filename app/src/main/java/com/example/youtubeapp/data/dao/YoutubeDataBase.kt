package com.example.youtubeapp.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.youtubeapp.converter.*
import com.example.youtubeapp.domain.models.DetailPlayList
import com.example.youtubeapp.domain.models.PlaylistInfo
import com.example.youtubeapp.domain.models.PlaylistItem

@Database(entities = [PlaylistItem::class], version = 1, exportSchema = false)
@TypeConverters(
    ContentDetailsConverter::class,
    SnippetConverter::class
)
abstract class YoutubeDataBase : RoomDatabase() {

    abstract fun wordDao(): YoutubeDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: YoutubeDataBase? = null

        fun getDatabase(context: Context): YoutubeDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
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