package com.example.heartconnect.utils

class RegisterUtil {
    fun isAdded(hobbies: ArrayList<String>, hobby: String): Boolean {
        return hobbies.contains(hobby)
    }
}