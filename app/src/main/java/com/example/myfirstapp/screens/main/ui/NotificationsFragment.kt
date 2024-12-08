package com.example.myfirstapp.screens.main.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myfirstapp.databinding.FragmentNotificationsBinding
import com.google.android.ads.nativetemplates.NativeTemplateStyle
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions


private const val LOG_TAG = "NotificationsFragment"
private const val NATIVE_AD_UNIT_ID = "ca-app-pub-3940256099942544/2247696110"

class NotificationsFragment : Fragment() {
    private lateinit var binding: FragmentNotificationsBinding
    private var nativeAd: NativeAd? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadNativeAd()
    }

    override fun onDestroy() {
        nativeAd?.destroy()
        super.onDestroy()
    }

    private fun loadNativeAd() {
        val adRequest = AdRequest.Builder().build()
        val adLoader = AdLoader.Builder(requireContext(), NATIVE_AD_UNIT_ID)
            .forNativeAd { nativeAd ->
                val styles =
                    NativeTemplateStyle.Builder().build()
                binding.apply {
                    tempViewAd.apply {
                        setStyles(styles)
                        setNativeAd(nativeAd)
                    }
                }
            }
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(error: LoadAdError) {
                    Log.e(LOG_TAG, error.message)
                    Log.e(LOG_TAG, "Ad failed to load: ${error.code}")
                }

                override fun onAdClicked() {
                    super.onAdClicked()
                    Log.d(LOG_TAG, "Ad clicked")
                }
            })
            .withNativeAdOptions(
                NativeAdOptions.Builder()
                    .build()
            )
            .build()

        adLoader.loadAd(adRequest)
    }

    /**
    private fun populateNativeAdView(nativeAd: NativeAd) {
    val nativeAdBinding = NativeAdBinding.inflate(layoutInflater)
    val nativeAdView = nativeAdBinding.ntAdView
    nativeAdBinding.let {
    nativeAdView.headlineView = nativeAdBinding.tvHeadline
    nativeAdView.bodyView = nativeAdBinding.tvBody
    nativeAdView.callToActionView = nativeAdBinding.btnAction
    nativeAdView.mediaView = nativeAdBinding.mdVideo

    (nativeAdView.headlineView as TextView).text = nativeAd.headline
    (nativeAdView.bodyView as TextView).text = nativeAd.body
    (nativeAdView.callToActionView as Button).text = nativeAd.callToAction

    nativeAdView.setNativeAd(nativeAd)
    }
    }
     */
}