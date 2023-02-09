package com.example.youtubeapp.presentation.ui.fragments.noInternet

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.youtubeapp.R
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.example.youtubeapp.base.BaseFragment
import com.example.youtubeapp.extensions.getConnectivityManager
import com.example.youtubeapp.extensions.isInternetConnected
import com.example.youtubeapp.extensions.showToast
import com.example.youtubeapp.presentation.ui.fragments.playlists.PlaylistViewModel
import kotlinx.android.synthetic.main.fragment_no_internet.*
import org.koin.android.ext.android.inject


class NoInternetFragment : BaseFragment<NoInternetViewModel>(R.layout.fragment_no_internet) {

    companion object {
        const val OFFLINE_STATE = "offline_state"
    }

    private fun offline() {
        btn_offline.setOnClickListener {
            var bundle = Bundle()
            bundle.putBoolean(OFFLINE_STATE,true)
            findNavController().navigate(R.id.action_noInternetFragment_to_playlistFragment,bundle)
        }
    }

    private fun isOnline() {
        btn_try_again.setOnClickListener {
            if (isInternetConnected(getConnectivityManager(requireContext()))){
                findNavController().navigate(R.id.action_noInternetFragment_to_playlistFragment)
            }else{
                context?.showToast("Нет подключения")
            }
        }
    }

    override fun getViewModule(): NoInternetViewModel = inject<NoInternetViewModel>().value

    override fun setUpView() {
        isOnline()
        offline()
    }

    override fun setUpViewModelObs() {
    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        player: YouTubePlayer?,
        wasRestored: Boolean
    ) {

    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        errorReason: YouTubeInitializationResult?
    ) {
    }
}