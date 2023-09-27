package com.example.heartconnect.utils

import android.util.Log

class RegisterUtil {
    fun isAdded(hobbies: ArrayList<String>, hobby: String): Boolean {
        Log.d("Hobby", "$hobbies")
        return hobbies.contains(hobby)
    }
}