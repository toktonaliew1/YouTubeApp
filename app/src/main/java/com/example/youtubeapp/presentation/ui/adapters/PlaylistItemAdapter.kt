package com.example.youtubeapp.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtubeapp.databinding.ItemPlaylistItemBinding
import com.example.youtubeapp.domain.models.PlaylistItem
import com.example.youtubeapp.presentation.diffutils.PlaylistItemDiffUtil


class PlaylistItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val oldItems = ArrayList<PlaylistItem>()
    var currentSelected: Int? = 0
    var addListener: ItemClickListener? = null

    inner class PlaylistItemHolder(itemView: ItemPlaylistItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val binding = itemView
        fun setData(data:PlaylistItem, selected: Boolean,
                    function: (Int) -> Unit, position: Int) {

            binding.root.isSelected = selected
            binding.root.setOnClickListener {
                function(position)
                if (!selected){
                    addListener?.onClick(data)
                }
            }

            binding.tvVideoTitle.text = data.snippet?.title
            binding.tvPublished.text = data.snippet?.publishedAt
            Glide.with(binding.root)
                .load(data.snippet?.thumbnails?.medium?.url)
                .into(binding.ivThumbnail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = ItemPlaylistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaylistItemHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val function = { pos: Int ->
            if (currentSelected == null || currentSelected != pos) {
                currentSelected = pos
                notifyDataSetChanged()
            }
        }
        (holder as PlaylistItemHolder).setData(oldItems[position],position == currentSelected, function, position)
    }

    override fun getItemCount(): Int {
        return oldItems.size
    }

    fun setData(newList: List<PlaylistItem>){
        val videoDiff = PlaylistItemDiffUtil(oldItems, newList)
        val diff = DiffUtil.calculateDiff(videoDiff)
        oldItems.addAll(newList)
        diff.dispatchUpdatesTo(this)
    }

    fun interface ItemClickListener {
        fun onClick(data:PlaylistItem)
    }
}