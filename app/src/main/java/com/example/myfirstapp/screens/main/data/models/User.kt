package com.example.myfirstapp.screens.main.data.models

data class User(
    val id: Long,
    val address: Address,
    val company: Company,
    val email: String,
    val name: String,
    val phone: String,
    val username: String,
    val website: String,
)