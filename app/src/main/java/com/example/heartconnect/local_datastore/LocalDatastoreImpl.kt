package com.example.heartconnect.local_datastore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class LocalDatastoreImpl(private val datastore: DataStore<Preferences>) : LocalDatastore {
    override fun getUserId(): Flow<String> {
        return datastore.data.catch { emit(emptyPreferences()) }.map {
            it[USER_ID_KEY] ?: ""
        }
    }

    override suspend fun saveUserId(id: String?) {
        datastore.edit {
            it[USER_ID_KEY] = id ?: ""
        }
    }

    override suspend fun removeUser() {
        datastore.edit {
            it[USER_ID_KEY] = ""
        }
    }
}