package com.example.myfirstapp.screens.main.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myfirstapp.screens.main.data.local.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}