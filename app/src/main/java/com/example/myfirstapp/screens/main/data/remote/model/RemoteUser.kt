package com.example.myfirstapp.screens.main.data.remote.model

data class RemoteUser(
    val id: Long,
    val address: RemoteAddress,
    val company: RemoteCompany,
    val email: String,
    val name: String,
    val phone: String,
    val username: String,
    val website: String,
)