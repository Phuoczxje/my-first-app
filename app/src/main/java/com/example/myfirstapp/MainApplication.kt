package com.example.myfirstapp

import android.app.Application
import com.example.myfirstapp.admob.AppOpenAdManager
import com.example.myfirstapp.common.di.appModule
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MainApplication : Application() {
    private lateinit var appOpenAdManager: AppOpenAdManager

    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.IO).launch {
            val requestConfiguration = RequestConfiguration.Builder()
                .setTestDeviceIds(mutableListOf("906B6BDACE756640C7671A400A3A79C9"))
                .build()
            MobileAds.setRequestConfiguration(requestConfiguration)
            MobileAds.initialize(this@MainApplication) {}
        }

        appOpenAdManager = AppOpenAdManager(this@MainApplication)

        startKoin {
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }
}