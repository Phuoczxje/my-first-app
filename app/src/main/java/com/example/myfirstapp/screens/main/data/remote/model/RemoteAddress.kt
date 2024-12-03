package com.example.myfirstapp.screens.main.data.remote.model

data class RemoteAddress(
    val city: String,
    val geo: RemoteGeo,
    val street: String,
    val suite: String,
    val zipcode: String
)