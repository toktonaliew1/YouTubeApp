package com.example.youtubeapp.presentation.ui.fragments.video

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapp.R
import com.example.youtubeapp.data.remote.network.Status
import com.example.youtubeapp.databinding.ActivityVideoItemBinding
import com.example.youtubeapp.domain.models.PlaylistItem
import com.example.youtubeapp.extensions.getConnectivityManager
import com.example.youtubeapp.extensions.isInternetConnected
import com.example.youtubeapp.presentation.ui.adapters.PlaylistItemAdapter
import com.example.youtubeapp.presentation.ui.fragments.details.DetailsFragment
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import org.koin.android.ext.android.inject


class VideoActivity : AppCompatActivity(), YouTubePlayerListener {

    companion object {
        const val VIDEO_ITEM = "playlist_id"
    }

    private lateinit var video: PlaylistItem
    private var _binding: ActivityVideoItemBinding? = null
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
        _binding = ActivityVideoItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//
//        intent?.let {
//            video = it.getSerializableExtra(VIDEO_ITEM) as PlaylistItem
//        }
//
//
//        binding.titleVideo.text = video.snippet?.title
//        binding.videoDescription.text = video.snippet?.description
//        binding.videoDescription.movementMethod = ScrollingMovementMethod()

        binding.icBack.setOnClickListener {

        }

        supportActionBar?.hide()

        lifecycle.addObserver(binding.youtubePlayer)

        binding.youtubePlayer.addYouTubePlayerListener(this)

//        playlistItemViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
//            .get(VideoDetailViewModel::class.java)

        val manager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)


        adapter.addListener = PlaylistItemAdapter.ItemClickListener { data ->
            Toast.makeText(this, "PLay", Toast.LENGTH_LONG).show()
            data.contentDetails?.videoId?.let { id ->
                youtubePlayer?.loadVideo(id, 0f)
            }
            binding.titleVideo.text = data.snippet?.title
            binding.videoDescription.text = data.snippet?.description
            binding.videoDescription.movementMethod = ScrollingMovementMethod()
        }

        playlistItemViewModel.isLoading.observe(this) {
            isLoading = it
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        playlistItemViewModel.isAllItemLoaded.observe(this) {
            isAllVideoLoaded = it
            if (it) Toast.makeText(this, "All video has been loaded", Toast.LENGTH_SHORT).show()
        }


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {

        }
        return true
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
            Log.e("DATA", playlistId + "" + videoId)
            playlistItemViewModel.getPlaylistItem(playlistId, videoId).observe(this) {
                when (it.status) {
                    Status.ERROR -> {
                        Log.e("ERROR", it.message.toString())
                    }
                    Status.SUCCESS -> {
                        Toast.makeText(this, "Play", Toast.LENGTH_SHORT).show()
                        if (!isPlayingVideo) {
                            isPlayingVideo = true
                            it?.data?.items?.get(0)?.contentDetails?.videoId?.let { it1 ->
                                Toast.makeText(this, it1, Toast.LENGTH_SHORT).show()
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
                        Log.e("ELSE", "else")
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