package com.example.myfirstapp

import android.app.Application
import com.example.myfirstapp.di.apiModule
import com.example.myfirstapp.di.userModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(userModule, apiModule)
        }
    }
}