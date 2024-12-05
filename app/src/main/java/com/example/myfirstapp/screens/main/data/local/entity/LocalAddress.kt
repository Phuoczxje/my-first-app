package com.example.myfirstapp.screens.main.data.local.entity

import androidx.room.Embedded

data class LocalAddress(
    val city: String,
    @Embedded val localGeo: LocalGeo,
    val street: String,
    val suite: String,
    val zipcode: String
)