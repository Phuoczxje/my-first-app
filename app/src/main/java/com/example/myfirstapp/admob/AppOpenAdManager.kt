package com.example.myfirstapp.admob

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import com.example.myfirstapp.MainApplication
import com.example.myfirstapp.screens.main.ui.MainActivity
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val LOG_TAG = "AppOpenAdManager"
private const val AD_UNIT_ID = "ca-app-pub-3940256099942544/9257395921"

class AppOpenAdManager(private val mainApplication: MainApplication) :
    Application.ActivityLifecycleCallbacks {
    private var appOpenAd: AppOpenAd? = null
    private var isShowingAd = false

    init {
        mainApplication.registerActivityLifecycleCallbacks(this)
        loadAd()
    }

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
    }

    override fun onActivityStarted(p0: Activity) {
        if (p0 is MainActivity) {
            CoroutineScope(Dispatchers.IO).launch {
                if (!isShowingAd && appOpenAd != null) {
                    withContext(Dispatchers.Main) {
                        showAdIfAvailable(p0)
                    }
                }
            }
        }
    }

    override fun onActivityResumed(p0: Activity) {
    }

    override fun onActivityPaused(p0: Activity) {
    }

    override fun onActivityStopped(p0: Activity) {
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
    }

    override fun onActivityDestroyed(p0: Activity) {
    }

    fun loadAd() {
        val adRequest = AdRequest.Builder().build()
        AppOpenAd.load(
            mainApplication,
            AD_UNIT_ID,
            adRequest,
            AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
            object : AppOpenAd.AppOpenAdLoadCallback() {
                override fun onAdLoaded(ad: AppOpenAd) {
                    appOpenAd = ad
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    Log.e(LOG_TAG, loadAdError.message)
                    Log.e(LOG_TAG, "${loadAdError.code}")
                }
            }
        )
    }

    private fun showAdIfAvailable(activity: Activity) {
        if (isShowingAd || appOpenAd == null) {
            loadAd()
            return
        }
        appOpenAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                appOpenAd = null
                isShowingAd = false
                loadAd()
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                Log.e(LOG_TAG, adError.message)
                Log.e(LOG_TAG, "${adError.code}")
            }

            override fun onAdShowedFullScreenContent() {
                isShowingAd = true
            }
        }
        try {
            appOpenAd?.show(activity)
        } catch (e: Exception) {
            Log.e(LOG_TAG, "Error Ads Open App: ${e.message}")
        }
    }
}