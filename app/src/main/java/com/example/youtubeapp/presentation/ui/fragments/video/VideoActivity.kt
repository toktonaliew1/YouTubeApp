package com.example.youtubeapp.presentation.ui.fragments.video


import android.content.Intent
import android.os.Bundle
import android.provider.VoicemailContract
import android.text.method.ScrollingMovementMethod

import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.cleanarchicture.domain.repositories.YouTubeRepository
import com.example.youtubeapp.R
import com.example.youtubeapp.data.remote.network.Resource
import com.example.youtubeapp.data.remote.network.Status
import com.example.youtubeapp.data.repository.YoutubeRepositoryImpl
import com.example.youtubeapp.data.repository.YoutubeRepositoryImpl.Companion.YOUTUBE_API_KEY

import com.example.youtubeapp.databinding.ActivityVideoBinding
import com.example.youtubeapp.domain.models.PlaylistInfo

import com.example.youtubeapp.domain.models.PlaylistItem
import com.example.youtubeapp.extensions.getConnectivityManager
import com.example.youtubeapp.extensions.gone
import com.example.youtubeapp.extensions.isInternetConnected
import com.example.youtubeapp.extensions.logger
import com.example.youtubeapp.presentation.ui.activity.MainActivity

import com.example.youtubeapp.presentation.ui.adapters.PlaylistItemAdapter
import com.example.youtubeapp.presentation.ui.fragments.details.DetailsFragment
import com.google.android.youtube.player.YouTubeBaseActivity

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import kotlinx.android.synthetic.main.activity_video.view.*
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_playlist.*
import kotlinx.android.synthetic.main.fragment_playlist.toolbar_info
import org.koin.android.ext.android.inject


class VideoActivity : AppCompatActivity(), YouTubePlayerListener {

    companion object {
        const val VIDEO_ITEM = "playlist_id"
    }

    private lateinit var video: PlaylistItem
    private var _binding: ActivityVideoBinding? = null
    private val binding get() = _binding!!
    private val playlistItemViewModel: VideoDetailViewModel
        get() = inject<VideoDetailViewModel>().value
    private var youtubePlayer: YouTubePlayer? = null
    private val adapter = PlaylistItemAdapter()
    private var isLoading = false
    private var isAllVideoLoaded = false
    private var isPlayingVideo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchData()
        setData()
        onClickListenerToolbar()
        statusBar()

    }

    private fun statusBar(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.hide()
    }

    private fun fetchData() {
        playlistItemViewModel.isLoading.observe(this) {
            isLoading = it
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        playlistItemViewModel.isAllItemLoaded.observe(this) {
            isAllVideoLoaded = it
            if (it) Toast.makeText(this, "All video has been loaded", Toast.LENGTH_SHORT).show()
        }


        lifecycle.addObserver(binding.youtubePlayer)

        binding.youtubePlayer.addYouTubePlayerListener(this)
    }

    private fun setData() {
        adapter.addListener = PlaylistItemAdapter.ItemClickListener { data ->
            data.contentDetails?.videoId?.let { id ->
                youtubePlayer?.loadVideo(id, 0f)
            }
            binding.titleVideo.text = data.snippet?.title
            binding.videoDescription.text = data.snippet?.description
            binding.videoDescription.movementMethod = ScrollingMovementMethod()
        }
    }


    private fun onClickListenerToolbar() {
      

    }
    override fun onApiChange(youTubePlayer: YouTubePlayer) {

    }

    override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {

    }

    override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {

    }

    override fun onPlaybackQualityChange(
        youTubePlayer: YouTubePlayer,
        playbackQuality: PlayerConstants.PlaybackQuality
    ) {

    }

    override fun onPlaybackRateChange(
        youTubePlayer: YouTubePlayer,
        playbackRate: PlayerConstants.PlaybackRate
    ) {
    }


    override fun onReady(youTubePlayer: YouTubePlayer) {
        youtubePlayer = youTubePlayer
        intent?.let { intent ->
            val playlistId = intent.getStringExtra("playlist_id")!!
            val videoId = intent.getStringExtra("video_id")!!
            playlistItemViewModel.getPlaylistItem(playlistId, videoId).observe(this) {
                when (it.status) {
                    Status.SUCCESS -> {
                        if (!isPlayingVideo) {
                            isPlayingVideo = true
                            it?.data?.items?.get(0)?.contentDetails?.videoId?.let { it1 ->
                                youtubePlayer!!.loadVideo(it1, 0f)
                            }
                            binding.titleVideo.text =
                                it?.data?.items?.get(0)?.snippet?.title
                            binding.videoDescription.text =
                                it?.data?.items?.get(0)?.snippet?.description
                            binding.videoDescription.movementMethod =
                                ScrollingMovementMethod()
                        }
                    }
                    else -> {

                    }
                }
            }
        }
    }

    override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {
        if (state == PlayerConstants.PlayerState.PLAYING) isPlayingVideo = true
    }

    override fun onVideoDuration(youTubePlayer: YouTubePlayer, duration: Float) {

    }

    override fun onVideoId(youTubePlayer: YouTubePlayer, videoId: String) {
        isPlayingVideo = videoId.isNotEmpty()
    }

    override fun onVideoLoadedFraction(youTubePlayer: YouTubePlayer, loadedFraction: Float) {

    }
}