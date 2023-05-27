package com.example.youtubeapp.presentation.ui.fragments.onBoarding

import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.youtubeapp.domain.models.onBoarding.OnBoardingItem
import com.example.youtubeapp.R
import com.example.youtubeapp.base.BaseFragmentOnBoard
import com.example.youtubeapp.databinding.FragmentOnBoardingBinding
import com.example.youtubeapp.presentation.ui.adapters.OnBoardingItemAdapter

class OnBoardingFragment : BaseFragmentOnBoard<FragmentOnBoardingBinding>(R.layout.fragment_on_boarding){

    private val viewPagerAdapter = OnBoardingItemAdapter(this::onItemClick)

    override val binding by viewBinding(FragmentOnBoardingBinding::bind)

    override fun setupListeners() {
        binding.onboardingViewPager.setOnClickListener {
            binding.onboardingViewPager.setCurrentItem(binding.onboardingViewPager.currentItem + 1, true)
        }
        binding.skip.setOnClickListener{
            findNavController().navigate(R.id.action_onBoardingFragment_to_playlistFragment)
        }
    }

    override fun setUpViews() {
        setupAdapter()
    }

    private fun setupAdapter() {
        binding.onboardingViewPager.adapter = viewPagerAdapter
        viewPagerAdapter.setData(list = setOnBoardingItems())
        binding.dotsIndicator.setViewPager2(binding.onboardingViewPager)
    }

    private fun setOnBoardingItems(): ArrayList<OnBoardingItem> {
        val list: ArrayList<OnBoardingItem> = arrayListOf()
        list.add(
            OnBoardingItem(
                R.drawable.unsplash,
                "Smart Wallet", "Managing your money can be the easiest thing you do all day.",
                "NEXT"
            )
        )
        list.add(
            OnBoardingItem(
                R.drawable.pager2, "Effortless Budgeting", "Customize your budget categories and stay on top of your spending 24/7.",
                "NEXT"
            )
        )
        list.add(
            OnBoardingItem(
                R.drawable.youtube,
                "Automatic Savings", "Set your savings goal, and let Empower figure out how to get you there.",
                "START"
            )
        )

        return list
    }

    private fun onItemClick(position: Int) {

        when (position) {
            0 -> {
                binding.onboardingViewPager.setCurrentItem(1, true)
            }
            1 -> {
                binding.onboardingViewPager.setCurrentItem(2, true)
            }
            2 -> {

                findNavController().navigate(R.id.action_onBoardingFragment_to_playlistFragment)

            }
        }

    }

}