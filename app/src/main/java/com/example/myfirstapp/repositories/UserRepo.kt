package com.example.myfirstapp.repositories

import androidx.lifecycle.MutableLiveData
import com.example.myfirstapp.models.UserModel

class UserRepo {
    private var userData = UserModel("1", "Nguyễn Văn A", "00000000")

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