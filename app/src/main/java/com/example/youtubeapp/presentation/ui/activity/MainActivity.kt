package com.example.youtubeapp.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.example.youtubeapp.R
import com.example.youtubeapp.base.BaseActivityMain
import com.example.youtubeapp.data.repository.YoutubeRepository
import com.example.youtubeapp.extensions.getConnectivityManager
import com.example.youtubeapp.extensions.isInternetConnected
import com.example.youtubeapp.presentation.ui.fragments.playlists.PlaylistViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(){

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(2000)
        setTheme(R.style.Theme_YouTubeApp)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        checkForInternet()
    }

    private fun checkForInternet() {
        if (!isInternetConnected(getConnectivityManager(baseContext))){
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            navController.navigate(R.id.action_playlistFragment_to_noInternetFragment)
        }
    }
}