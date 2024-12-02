package com.example.myfirstapp.common.di

import androidx.room.Room
import com.example.myfirstapp.common.database.AppDatabase
import com.example.myfirstapp.screens.main.data.remote.ApiService
import com.example.myfirstapp.screens.main.data.repo.UserRepo
import com.example.myfirstapp.screens.main.ui.UserViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val API_URL = "https://jsonplaceholder.typicode.com"

val appModule = module {
    single {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(API_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(ApiService::class.java)
    }

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "database")
            .build()
    }

    single { get<AppDatabase>().userDao() }

    single { UserRepo(get(), get()) }
    viewModel { UserViewModel(get()) }
}
