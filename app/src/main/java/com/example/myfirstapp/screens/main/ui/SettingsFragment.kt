package com.example.myfirstapp.screens.main.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myfirstapp.databinding.FragmentSettingsBinding
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

private const val LOG_TAG = "SettingsFragment"
private const val INTERSTITIAL_AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712"
private const val REWARD_AD_UNIT_ID = "ca-app-pub-3940256099942544/5224354917"

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private var mInterstitialAd: InterstitialAd? = null
    private var rewardedAd: RewardedAd? = null
    private var balance = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadInterstitialAd()
        loadRewardedAd()

        binding.apply {
            btnIntersAd.setOnClickListener {
                showInterstitialAd()
            }

            btnRewardAd.setOnClickListener {
                showRewardedAd()
            }
        }
    }

    private fun showInterstitialAd() {
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(requireActivity())
            return
        }
        Log.d(LOG_TAG, "The interstitial ad wasn't ready yet.")
    }

    private fun showRewardedAd() {
        rewardedAd?.let { ad ->
            ad.show(requireActivity()) { rewardItem ->
                balance += 10
                setBalance()
                Log.d(LOG_TAG, "User earned the reward. ${rewardItem.amount}")
            }
        } ?: run {
            Log.d(LOG_TAG, "The rewarded ad wasn't ready yet.")
        }
    }

    private fun loadRewardedAd() {
        val adRequest = AdRequest.Builder().build()
        RewardedAd.load(
            requireContext(),
            REWARD_AD_UNIT_ID,
            adRequest,
            object : RewardedAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(LOG_TAG, adError.message)
                    Log.d(LOG_TAG, "${adError.code}")
                    rewardedAd = null
                }

                override fun onAdLoaded(ad: RewardedAd) {
                    Log.d(LOG_TAG, "Ad was loaded.")
                    rewardedAd = ad
                    addCallbackToRewardedAd()
                }
            })
    }

    private fun loadInterstitialAd() {
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(
            requireContext(),
            INTERSTITIAL_AD_UNIT_ID,
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(LOG_TAG, adError.message)
                    Log.d(LOG_TAG, "${adError.code}")
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d(LOG_TAG, "Ad was loaded.")
                    mInterstitialAd = interstitialAd
                    addCallbackToInterstitialAd()
                }
            })
    }

    private fun addCallbackToRewardedAd() {
        rewardedAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdClicked() {
                Log.d(LOG_TAG, "Ad was clicked.")
            }

            override fun onAdDismissedFullScreenContent() {
                Log.d(LOG_TAG, "Ad dismissed fullscreen content.")
                rewardedAd = null
            }

            override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                Log.e(LOG_TAG, "Ad failed to show fullscreen content.")
                rewardedAd = null
            }

            override fun onAdImpression() {
                Log.d(LOG_TAG, "Ad recorded an impression.")
            }

            override fun onAdShowedFullScreenContent() {
                Log.d(LOG_TAG, "Ad showed fullscreen content.")
            }
        }
    }

    private fun addCallbackToInterstitialAd() {
        mInterstitialAd?.fullScreenContentCallback =
            object : FullScreenContentCallback() {
                override fun onAdClicked() {
                    Log.d(LOG_TAG, "Ad was clicked.")
                }

                override fun onAdDismissedFullScreenContent() {
                    Log.d(LOG_TAG, "Ad dismissed fullscreen content.")
                    mInterstitialAd = null
                }

                override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                    Log.e(LOG_TAG, "Ad failed to show fullscreen content.")
                    mInterstitialAd = null
                }

                override fun onAdImpression() {
                    Log.d(LOG_TAG, "Ad recorded an impression.")
                }

                override fun onAdShowedFullScreenContent() {
                    Log.d(LOG_TAG, "Ad showed fullscreen content.")
                }
            }
    }

    @SuppressLint("SetTextI18n")
    fun setBalance() {
        binding.let {
            it.tvBalance.text = "Số dư: $balance đồng"
        }
    }
}