package com.example.youtubeapp.presentation.ui.adapters


import android.view.LayoutInflater

import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapp.domain.models.onBoarding.OnBoardingItem

import com.example.youtubeapp.databinding.OnboardingItemBinding


class OnBoardingItemAdapter(private val onboardigItems: (position: Int) -> Unit) :
    RecyclerView.Adapter<OnBoardingItemAdapter.OnBoardingItemViewHolder>() {
    var list: ArrayList<OnBoardingItem> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingItemViewHolder {
        return OnBoardingItemViewHolder(
            OnboardingItemBinding.inflate
                (LayoutInflater.from(parent.context), parent, false)
        )
    }

    fun setData(list: ArrayList<OnBoardingItem>) {
        this.list = list
    }


    inner class OnBoardingItemViewHolder(private val binding: OnboardingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(onboardingItem: OnBoardingItem) {
            binding.imageOnBoarding.setImageResource(onboardingItem.onBoardingImage)
            binding.textTitle.text = onboardingItem.title
            binding.textDescription.text = onboardingItem.description
            binding.buttonGEtStarted.text = onboardingItem.next
            binding.buttonGEtStarted.setOnClickListener{
                   onboardigItems(absoluteAdapterPosition)
            }
        }
    }


    override fun onBindViewHolder(holder: OnBoardingItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}