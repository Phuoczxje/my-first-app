package com.example.myfirstapp

import android.app.Application
import com.example.myfirstapp.admob.AppOpenAdManager
import com.example.myfirstapp.common.di.appModule
import com.google.android.gms.ads.MobileAds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MainApplication : Application() {
    private lateinit var appOpenAdManager: AppOpenAdManager

    override fun onCreate() {
        super.onCreate()
        appOpenAdManager = AppOpenAdManager(this@MainApplication)

        CoroutineScope(Dispatchers.IO).launch {
            MobileAds.initialize(this@MainApplication) {}
        }

        startKoin {
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }
}