package com.example.heartconnect.features.data.datasources

import com.example.heartconnect.features.data.models.feed.FeedModel
import com.example.heartconnect.firebase.FirebaseConfig
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRemoteDatasourceImpl : UserRemoteDatasource {
    override suspend fun getHomeUsers(id: String): List<FeedModel> {
        try {
            val querySnapshot = FirebaseConfig().db.collection("Users").get().await()
            val allUsers = querySnapshot.documents.map { documentSnapshot ->
                val data = documentSnapshot.data ?: emptyMap()
                val docId = documentSnapshot.id
                val name = data["name"] as? String ?: ""
                val birthYear = data["birthYear"] as? String ?: ""
                val hobbies = data["hobbies"] as? List<String> ?: listOf()
                val profileImage = data["image"] as? String ?: ""
                FeedModel(
                    name = name, birthYear = birthYear, hobbies = hobbies, uid = docId,
                    profileImage = profileImage
                )
            }
            return allUsers
        } catch (ex: Exception) {
            throw ex
        }
    }
}