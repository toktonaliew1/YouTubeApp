package com.example.youtubeapp.presentation.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import androidx.navigation.fragment.findNavController
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController

import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.youtubeapp.R
import com.example.youtubeapp.databinding.ActivityMainBinding
import com.example.youtubeapp.extensions.getConnectivityManager
import com.example.youtubeapp.extensions.isInternetConnected

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(2000)
        setTheme(R.style.Theme_YouTubeApp)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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