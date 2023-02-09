package com.example.youtubeapp.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapp.R

import com.example.youtubeapp.domain.models.PlaylistItem
import com.example.youtubeapp.extensions.loadImage
import com.example.youtubeapp.presentation.playlistClick.OnPlaylistClickListener
import kotlinx.android.synthetic.main.playlist_item.view.*
import kotlinx.android.synthetic.main.video_item.view.*

class PlaylistAdapter(var listener : OnPlaylistClickListener)
    : RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>() {

    private var list = ArrayList<PlaylistItem>()

    fun add(data:MutableList<PlaylistItem>){
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder
            = PlaylistViewHolder(LayoutInflater.from(parent.context)
        .inflate(R.layout.playlist_item,parent,false))

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.onBind(list[position])
        holder.itemView.setOnClickListener {
            listener.onClick(list[position])
        }
    }

    override fun getItemCount(): Int  = list.size

    class PlaylistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(playlistItem: PlaylistItem){
            itemView.apply {

                playlist_name.text = playlistItem.snippet?.title
                playlist_amount.text = (playlistItem.contentDetails?.itemCount.toString() + " video series")
                playlist_image.loadImage(playlistItem.snippet?.thumbnails?.medium?.url, 10)
            }
        }

    }

}