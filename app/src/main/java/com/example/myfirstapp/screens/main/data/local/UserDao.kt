package com.example.myfirstapp.screens.main.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.myfirstapp.screens.main.data.models.User

@Dao
interface UserDao {
    @Transaction
    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

    @Insert
    suspend fun insertUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<User>)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}