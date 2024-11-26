package com.example.myfirstapp

import android.content.Context
import android.content.Intent

/** Direct to an other activity */
fun <T> Context.directToOtherActivity(destination: Class<T>) {
    val intent = Intent(this, destination)
    startActivity(intent)
}