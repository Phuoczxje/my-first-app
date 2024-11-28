package com.example.myfirstapp.repo

import androidx.lifecycle.MutableLiveData
import com.example.myfirstapp.models.UserModel

class UserRepo {
    private val userData = UserModel("1", "Nguyễn Văn A", "00000000")

    fun getUsername() =
        MutableLiveData<UserModel>().apply {
            value = userData
        }

    fun updateUsername(name: String) =
        MutableLiveData<UserModel>().apply {
            userData.name = name
            value = userData
        }
}