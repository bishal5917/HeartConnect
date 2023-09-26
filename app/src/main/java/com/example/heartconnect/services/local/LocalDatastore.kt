package com.example.heartconnect.services.local

import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow

val USER_ID_KEY = stringPreferencesKey("user_id")

interface LocalDatastore {

    fun getUserId(): Flow<String>

    suspend fun saveUserId(id: String?)

    suspend fun removeUser()

}