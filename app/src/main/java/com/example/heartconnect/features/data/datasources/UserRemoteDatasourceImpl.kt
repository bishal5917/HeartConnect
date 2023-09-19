package com.example.heartconnect.features.data.datasources

import com.example.heartconnect.features.data.models.conversation.ConversationModel
import com.example.heartconnect.features.data.models.feed.FeedModel
import com.example.heartconnect.firebase.FirebaseConfig
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRemoteDatasourceImpl : UserRemoteDatasource {
    override suspend fun getHomeUsers(id: String): List<FeedModel> {
        try {
            val querySnapshot =
                FirebaseConfig().db.collection("Users").whereNotEqualTo(FieldPath.documentId(), id)
                    .get().await()
            val allUsers = querySnapshot.documents.map { documentSnapshot ->
                val data = documentSnapshot.data ?: emptyMap()
                val docId = documentSnapshot.id
                val name = data["name"] as? String ?: ""
                val birthYear = data["birthYear"] as? String ?: ""
                val hobbies = data["hobbies"] as? List<String> ?: listOf()
                val profileImage = data["image"] as? String ?: ""
                FeedModel(
                    name = name,
                    birthYear = birthYear,
                    hobbies = hobbies,
                    uid = docId,
                    profileImage = profileImage
                )
            }
            return allUsers
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun getConversations(id: String): List<ConversationModel> {
        try {
            val querySnapshot =
                FirebaseConfig().db.collection("Convos").whereArrayContains("members", id).get()
                    .await()
            val allUsers = querySnapshot.documents.map { documentSnapshot ->
                val data = documentSnapshot.data ?: emptyMap()
                val docId = documentSnapshot.id
                val members = data["members"] as? List<String>
                val friendId = members?.firstOrNull { it != id }
                val friendData =
                    FirebaseConfig().db.collection("Users").document(friendId ?: "").get().await()
                val friendName = friendData["name"] as? String ?: ""
                val friendImage = friendData["image"] as? String ?: ""
                ConversationModel(
                    convoId = docId,
                    members = members,
                    friendName = friendName,
                    friendImage = friendImage,
                )
            }
            return allUsers
        } catch (ex: Exception) {
            throw ex
        }
    }
}