package com.example.youtubeapp.presentation.ui.onBoarding

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.splashscreen.domain.models.OnBoardingItem
import com.example.youtubeapp.R
import com.example.youtubeapp.databinding.ActivityOnBaordingBinding
import com.example.youtubeapp.presentation.ui.activity.MainActivity
import com.example.youtubeapp.presentation.ui.adapters.OnBoardingItemAdapter
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.android.synthetic.main.activity_on_baording.*
import javax.inject.Inject




class OnBoarding : AppCompatActivity() {

    private lateinit var onBoardingItemsAdapter: OnBoardingItemAdapter
    private lateinit var indicatorsContainer: LinearLayout
    private lateinit var binding: ActivityOnBaordingBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(2000)

        setTheme(R.style.Theme_YouTubeApp)
        super.onCreate(savedInstanceState)

        binding = ActivityOnBaordingBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_on_baording)
        setOnBoardingItems()
        setupIndicator()
        setCurrentIndicator(0)
        setupPageChangeCallback(0)
    }



    private fun setOnBoardingItems() {
        onBoardingItemsAdapter = OnBoardingItemAdapter(
            listOf(
                OnBoardingItem(
                    onBoardingImage = R.drawable.unsplash,
                    title = "Smart Wallet",
                    description = "Managing your money can be the easiest thing you do all day.",
                    next = "next"


                ),
                OnBoardingItem(
                    onBoardingImage = R.drawable.pager2,
                    title = "Effortless Budgeting",
                    description = "Customize your budget categories and stay on top of your spending 24/7.",
                    next = "next"
                ),
                OnBoardingItem(
                    onBoardingImage = R.drawable.youtube,
                    title = "Automatic Savings",
                    description = "Set your savings goal, and let Empower figure out how to get you there.",
                    next = "get started"
                )
            )
        )
        val onboardingViewPager = findViewById<ViewPager2>(R.id.onboardingViewPager)
        onboardingViewPager.adapter = onBoardingItemsAdapter



        onboardingViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        (onboardingViewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER
        findViewById<MaterialButton>(R.id.buttonGEtStarted).setOnClickListener {
            if (onboardingViewPager.currentItem + 1 < onBoardingItemsAdapter.itemCount) {

                onboardingViewPager.currentItem += 1
            } else {

                navigateToHomeActivity()
            }
        }
        findViewById<TextView>(R.id.skip).setOnClickListener {
            navigateToHomeActivity()
        }
        findViewById<MaterialButton>(R.id.buttonGEtStarted).setOnClickListener {

        }


    }


    private fun navigateToHomeActivity() {
        startActivity(Intent(applicationContext, MainActivity::class.java))
        finish()
    }

    private fun setupIndicator() {
        indicatorsContainer = findViewById(R.id.indicatorsContainer)
        val indicators = arrayOfNulls<ImageView>(onBoardingItemsAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8, 0, 0, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_background
                    )
                )
                it.layoutParams = layoutParams
                indicatorsContainer.addView(it)
            }
        }
    }

    private fun setCurrentIndicator(position: Int) {
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorsContainer.getChildAt(i) as ImageView


            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background,
                    )
                )


            } else
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_background
                    )
                )
            buttonGEtStarted.setText(
                "открыть"
            )
        }
    }

    private fun setupPageChangeCallback(positionn: Int) {

        binding.onboardingViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }


            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }

        })
    }


}

