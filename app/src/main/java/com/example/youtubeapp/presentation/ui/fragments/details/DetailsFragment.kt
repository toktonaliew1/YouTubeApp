package com.example.youtubeapp.presentation.ui.fragments.details

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.youtubeapp.R
import com.example.youtubeapp.base.BaseFragment
import com.example.youtubeapp.data.remote.network.Resource
import com.example.youtubeapp.data.remote.network.Status
import com.example.youtubeapp.domain.models.PlaylistInfo
import com.example.youtubeapp.domain.models.PlaylistItem
import com.example.youtubeapp.extensions.*
import com.example.youtubeapp.presentation.playlistClick.OnPlaylistClickListener
import com.example.youtubeapp.presentation.ui.adapters.DetailAdapter
import com.example.youtubeapp.presentation.ui.fragments.playlists.PlaylistFragment
import com.example.youtubeapp.presentation.ui.fragments.playlists.PlaylistViewModel
import com.example.youtubeapp.presentation.ui.fragments.video.VideoActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.playlist_item.view.*
import org.koin.android.ext.android.inject

class DetailsFragment : BaseFragment<DetailsViewModel>(R.layout.fragment_detail),
    OnPlaylistClickListener {


    private lateinit var videoList: PlaylistItem
    private lateinit var adapter: DetailAdapter
    private var nextPageToken: String? = null

    override fun getViewModule(): DetailsViewModel =
        inject<DetailsViewModel>().value

    override fun setUpView() {
        initView()
        initRecycler()
        fetchData()
        pagging()
    }

    override fun setUpViewModelObs() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            videoList = it.getSerializable(PlaylistFragment.PLAYLIST_ITEM) as PlaylistItem
        }
    }

    private fun initView() {
        playlist_name.text = videoList.snippet?.title
        image_view.loadImage(videoList.snippet?.thumbnails?.medium?.url)
    }

    private fun initRecycler() {
        adapter = DetailAdapter(this)
        video_list_recycler.adapter = adapter
    }

    private fun fetchData() {
        mViewModule!!.getVideoListFromPlaylist(videoList.id.toString())
            .observe(viewLifecycleOwner, Observer {
                statusCheck(it)
            })
    }

    private fun pagging() {
        nested_scroll_video.setOnScrollChangeListener { nested: NestedScrollView, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == nested.getChildAt(0).measuredHeight - nested.measuredHeight) {
                if (nextPageToken != null) {
                    fetchNextData(nextPageToken!!)
                }
            }
        }
    }

    private fun fetchNextData(nextPageToken: String) {
        mViewModule!!.getNextVideoListFromPlaylist(videoList.id.toString(), nextPageToken)
            .observe(viewLifecycleOwner, Observer {
                if (it?.data?.nextPageToken == null) {
                    this.nextPageToken = null
                }
                statusCheck(it)
            })
    }

    private fun setData(resource: Resource<PlaylistInfo>) {
        resource.data?.items?.let { it1 -> adapter.add(it1) }
        nextPageToken = resource.data?.nextPageToken
        progress_bar.gone()
    }

    private fun statusCheck(resource: Resource<PlaylistInfo>) {
        when (resource.status) {
            Status.SUCCESS -> setData(resource)
            Status.LOADING -> progress_bar.visible()
            Status.ERROR -> logger("error", resource.message.toString())
        }
    }

    override fun onClick(item: PlaylistItem) {
        if (isInternetConnected(getConnectivityManager(requireContext()))) {
            val intent = Intent(requireContext(), VideoActivity::class.java)
            intent.putExtra("playlist_id", videoList.id)
            intent.putExtra("video_id", item.contentDetails?.videoId)
            requireActivity().startActivity(intent)
        } else {
            findNavController().navigate(R.id.action_playlistFragment_to_noInternetFragment)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            findNavController().navigate(R.id.action_playlistFragment_to_noInternetFragment)
        }
        return true
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