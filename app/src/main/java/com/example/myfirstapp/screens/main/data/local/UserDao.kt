package com.example.myfirstapp.screens.main.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.myfirstapp.screens.main.data.local.entity.UserEntity

@Dao
interface UserDao {
    @Transaction
    @Query("SELECT * FROM users")
    suspend fun getUsers(): List<UserEntity>

    @Transaction
    @Query("SELECT * FROM users WHERE id=:id")
    suspend fun getUser(id: Long): UserEntity?

    @Update
    suspend fun updateUser(user: UserEntity): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)
}