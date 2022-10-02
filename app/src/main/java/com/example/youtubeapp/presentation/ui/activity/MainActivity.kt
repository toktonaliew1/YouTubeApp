package com.example.youtubeapp.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.youtubeapp.R
import com.example.youtubeapp.base.BaseActivity
import com.example.youtubeapp.base.BaseActivityMain
import com.example.youtubeapp.extensions.getConnectivityManager
import com.example.youtubeapp.extensions.isInternetConnected

class MainActivity : BaseActivityMain(R.layout.activity_main){

    override fun onCreate(savedInstanceState: Bundle?) {
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