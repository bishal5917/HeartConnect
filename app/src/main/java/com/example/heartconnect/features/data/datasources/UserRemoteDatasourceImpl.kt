package com.example.heartconnect.features.data.datasources

import com.example.heartconnect.R
import com.example.heartconnect.features.data.models.HomeUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import androidx.compose.ui.res.painterResource

class UserRemoteDatasourceImpl : UserRemoteDatasource {
    override suspend fun getHomeUsers(id: String): List<HomeUser> {
        val db = FirebaseFirestore.getInstance()
        try {
            val querySnapshot = db.collection("Users").get().await()
            val allUsers = querySnapshot.documents.map { documentSnapshot ->
                val data = documentSnapshot.data ?: emptyMap()
                val name = data["name"] as? String ?: ""
                val birthYear = data["age"] as? String ?: ""
                val hobbies = data["hobbies"] as? List<String> ?: listOf()
                val profileImage = data["image"] as? String ?: ""
                HomeUser(
                    name = name, birthYear = birthYear, hobbies = hobbies, uid = "guagus",
                    imageResource = R.drawable.tiger,
                )
            }
            return allUsers
        } catch (ex: Exception) {
            throw ex
        }
    }


}