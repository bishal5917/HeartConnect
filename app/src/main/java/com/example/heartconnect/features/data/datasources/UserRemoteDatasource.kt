package com.example.heartconnect.features.data.datasources

import com.example.heartconnect.features.data.models.HomeUser
import com.google.firebase.firestore.FirebaseFirestore

interface UserRemoteDatasource {
    suspend fun getHomeUsers(id: String): List<HomeUser>
}