package com.example.myfirstapp.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myfirstapp.screens.main.data.local.UserDao
import com.example.myfirstapp.screens.main.data.models.Address
import com.example.myfirstapp.screens.main.data.models.Company
import com.example.myfirstapp.screens.main.data.models.Geo
import com.example.myfirstapp.screens.main.data.models.User

@Database(entities = [User::class, Address::class, Geo::class, Company::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}