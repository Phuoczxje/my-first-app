package com.example.myfirstapp.di

import com.example.myfirstapp.repo.UserRepo
import com.example.myfirstapp.viewmodels.UserViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val userModule = module {
    single { UserRepo() }
    viewModel { UserViewModel() }
}
