package com.example.youtubeapp.presentation.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.example.youtubeapp.domain.models.PlaylistItem


class PlaylistItemDiffUtil(
    private val oldList: List<PlaylistItem>,
    private val newList: List<PlaylistItem>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldVideo = oldList[oldItemPosition]
        val newVideo = newList[newItemPosition]
        return oldVideo.snippet?.title == newVideo.snippet?.title
    }
}