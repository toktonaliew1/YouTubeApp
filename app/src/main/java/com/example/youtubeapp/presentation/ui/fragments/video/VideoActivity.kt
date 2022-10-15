package com.example.youtubeapp.presentation.ui.fragments.video

import androidx.appcompat.app.AppCompatActivity
import com.example.youtubeapp.R

class VideoActivity : AppCompatActivity(R.layout.activity_video){

companion object {
    const val VIDEO_ITEM = "video_item"
    const val RECOVERY_REQUEST = 1
}
}
// BaseActivity<VideoDetailViewModel>(R.layout.activity_video,VideoDetailViewModel::class) {
//
//
//
//    override fun setupLiveData() {
//    }
//
//    override fun setupViews() {
//        getVideo()
//    }
//
//    private fun getVideo() {
//        initPlayer(item?.snippet?.resourceId?.videoId.toString())
//    }
//
//    private fun initPlayer(id: String) {
//        lifecycle.addObserver(player_view)
//        player_view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
//            override fun onReady(@NonNull youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer) {
//                youTubePlayer.loadVideo(id, 0F)
//            }
//        })
//        title_video.text = item?.snippet?.title
//        video_description.text = item?.snippet?.description
//    }
//
//    override fun setupFetchRequests() {
//    }
//
//    override fun onConfigurationChanged(newConfig: Configuration) {
//        super.onConfigurationChanged(newConfig)
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            supportActionBar?.hide()
//            window.setFlags(
//                WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN
//            )
//            player_view?.layoutParams {
//                width = ViewGroup.LayoutParams.MATCH_PARENT
//                height = ViewGroup.LayoutParams.WRAP_CONTENT
//            }
//        }
//
//        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
//            supportActionBar?.show()
//            player_view?.layoutParams {
//                width = ViewGroup.LayoutParams.MATCH_PARENT
//                height = ViewGroup.LayoutParams.MATCH_PARENT
//            }
//        }
//
//    }
//
//    companion object {
//        var item: DetailVideo? = null
//        const val VIDEO_ITEM = "video_item"
//        fun instanceActivity(activity: Activity?, video: DetailVideo) {
//            val intent = Intent(activity, VideoActivity::class.java)
//            intent.putExtra("video", item)
//            this.item = video
//            activity?.startActivity(intent)
//        }
//    }
//}
