package com.example.youtubeapp.presentation.ui.fragments.video

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.content.contentValuesOf
import com.example.youtubeapp.R
import com.example.youtubeapp.base.BaseActivity
import com.example.youtubeapp.domain.models.DetailVideo
import com.example.youtubeapp.extensions.layoutParams
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.activity_video.*
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class VideoActivity :
    BaseActivity<VideoDetailViewModel>(R.layout.activity_video, VideoDetailViewModel::class) {
    override fun setupLiveData() {
    }

    override fun setupViews() {
        getVideo()
    }

    private fun getVideo() {
        initPlayer(item?.snippet?.resourceId?.videoId.toString())
    }

    private fun initPlayer(id: String) {
        lifecycle.addObserver(player_view)
        player_view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer) {
                youTubePlayer.loadVideo(id, 0F)
            }
        })
        title_video.text = item?.snippet?.title
        video_description.text = item?.snippet?.description
    }

    override fun setupFetchRequests() {
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            supportActionBar?.hide()
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
            player_view?.layoutParams {
                width = ViewGroup.LayoutParams.MATCH_PARENT
                height = ViewGroup.LayoutParams.WRAP_CONTENT
            }
        }

        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            supportActionBar?.show()
            player_view?.layoutParams {
                width = ViewGroup.LayoutParams.MATCH_PARENT
                height = ViewGroup.LayoutParams.MATCH_PARENT
            }
        }
    }

    companion object {
        var item: DetailVideo? = null
        const val VIDEO_ITEM = "video_item"
        fun instanceActivity(activity: Activity?, video: DetailVideo) {
            val intent = Intent(activity, VideoActivity::class.java)
            intent.putExtra("video", item)
            this.item = video
            activity?.startActivity(intent)
        }
    }
}
