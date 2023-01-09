package com.example.youtubeapp.presentation.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.splashscreen.domain.models.OnBoardingItem
import com.example.youtubeapp.R
import com.example.youtubeapp.databinding.OnBaordingBinding
import com.example.youtubeapp.databinding.OnboardingItemContainerBinding

class OnBoardingItemAdapter(private val onboardigItems: List<OnBoardingItem>) :
    RecyclerView.Adapter<OnBoardingItemAdapter.OnBoardingItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingItemViewHolder {
        return OnBoardingItemViewHolder(
            OnboardingItemContainerBinding.inflate
                (LayoutInflater.from(parent.context), parent, false)
        )
    }

    inner class OnBoardingItemViewHolder(private val binding: OnboardingItemContainerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(onboardingItem: OnBoardingItem) {
            binding.imageOnBoarding.setImageResource(onboardingItem.onBoardingImage)
            binding.textTitle.text = onboardingItem.title
            binding.textDescription.text = onboardingItem.description
        }
    }


    override fun onBindViewHolder(holder: OnBoardingItemViewHolder, position: Int) {
        holder.bind(onboardigItems[position])
    }

    override fun getItemCount(): Int {
        return onboardigItems.size
    }

}