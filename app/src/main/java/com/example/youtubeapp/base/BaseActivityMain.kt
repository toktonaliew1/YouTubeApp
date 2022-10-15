package com.example.youtubeapp.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.example.youtubeapp.data.repository.YoutubeRepository

abstract class BaseActivityMain(@LayoutRes val layout:Int): AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
    }
}