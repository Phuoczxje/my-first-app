package com.example.myfirstapp.screens.main.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val username: String,
    val email: String,
    @Embedded val localAddress: LocalAddress,
    val phone: String,
    val website: String,
    @Embedded val localCompany: LocalCompany
)
