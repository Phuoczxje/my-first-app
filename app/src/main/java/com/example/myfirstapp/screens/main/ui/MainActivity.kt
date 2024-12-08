package com.example.myfirstapp.screens.main.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowMetrics
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myfirstapp.R
import com.example.myfirstapp.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.material.tabs.TabLayoutMediator

private const val LOG_TAG = "Admob"
private const val BANNER_AD_UNIT_ID = "ca-app-pub-3940256099942544/9214589741"

class MainActivity : FragmentActivity() {
    private lateinit var binding: ActivityMainBinding
    private var adView: AdView? = null
    private val fragList = listOf(
        ProfileFragment(),
        FriendsFragment(),
        NotificationsFragment(),
        SettingsFragment()
    )

    private val adSize: AdSize
        get() {
            val displayMetrics = this.resources.displayMetrics
            val adWidthPixels =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    val windowMetrics: WindowMetrics =
                        this.windowManager.currentWindowMetrics
                    windowMetrics.bounds.width()
                } else {
                    displayMetrics.widthPixels
                }
            val density = displayMetrics.density
            val adWidth = (adWidthPixels / density).toInt()
            return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
                this,
                adWidth
            )
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pagerAdapter = MainPagerAdapter(this@MainActivity)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.apply {
            vpMain.adapter = pagerAdapter
        }

        setContentView(binding.root)
        setupTabLayoutWithPager()
    }

    override fun onStart() {
        super.onStart()
        loadBanner()
    }

    override fun onResume() {
        super.onResume()
        adView?.resume()
    }

    override fun onPause() {
        super.onPause()
        adView?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        adView?.destroy()
    }

    private fun loadBanner() {
        val adView = AdView(this)
        adView.adUnitId = BANNER_AD_UNIT_ID
        adView.setAdSize(adSize)

        this.adView = adView
        addListenerToAdView()

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        binding.flBannerAd.run {
            removeAllViews()
            addView(adView)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun addListenerToAdView() {
        this.adView?.adListener = object : AdListener() {
            override fun onAdLoaded() {
                Log.d(LOG_TAG, "Ad loaded successfully.")
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.e(LOG_TAG, "Failed to load ad: ${adError.message}")
                Log.e(LOG_TAG, "Failed to load ad: ${adError.code}")
            }

            override fun onAdOpened() {
                Log.d(LOG_TAG, "Ad opened.")
            }

            override fun onAdClicked() {
                Log.d(LOG_TAG, "Ad clicked.")
            }

            override fun onAdClosed() {
                Log.d(LOG_TAG, "Ad closed.")
            }
        }
    }

    private fun setupTabLayoutWithPager() = binding.run {
        TabLayoutMediator(tlMain, vpMain) { tab, position ->
            when (position) {
                0 -> tab.setIcon(R.drawable.ic_profile)
                1 -> tab.setIcon(R.drawable.ic_friends)
                2 -> tab.setIcon(R.drawable.ic_notifications)
                3 -> tab.setIcon(R.drawable.ic_settings)
            }
        }.attach()
    }

    private inner class MainPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = fragList.size

        override fun createFragment(position: Int): Fragment {
            return fragList[position]
        }
    }
}


