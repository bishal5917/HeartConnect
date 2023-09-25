package com.example.heartconnect.features.data.datasources

import com.example.heartconnect.features.data.models.conversation.ConversationModel
import com.example.heartconnect.features.data.models.feed.FeedModel
import com.example.heartconnect.features.data.models.message.MessageModel
import com.example.heartconnect.features.data.models.message.MessageRequestModel
import com.example.heartconnect.firebase.FirebaseConfig
import com.example.heartconnect.model.CommonResponseModel
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.tasks.await
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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

    override suspend fun getMessages(messageRequestModel: MessageRequestModel): List<MessageModel> {
        try {
            val querySnapshot =
                FirebaseConfig().db.collection("Convos").document(messageRequestModel.convoId)
                    .collection("messages").get().await()
            val getFriendIdSnapshot =
                FirebaseConfig().db.collection("Convos").document(messageRequestModel.convoId).get()
                    .await()
            val members = getFriendIdSnapshot.get("members") as? List<String>
            val friendId = members?.firstOrNull { it != messageRequestModel.userId }
            val getFriendDetailSnapshot =
                FirebaseConfig().db.collection("Users").document(friendId ?: "").get().await()
            val friendName = getFriendDetailSnapshot.get("name") as String
            val friendImage = getFriendDetailSnapshot.get("image") as String
//            for listening in real time
            val ref = FirebaseConfig().db.collection("Convos").document(messageRequestModel.convoId)
                .collection("messages")
//            val allMessages = ArrayList<MessageModel>()
//            val listener = ref.addSnapshotListener { snapshot, exception ->
//                snapshot?.documents?.map { documentSnapshot ->
//                    val data = documentSnapshot.data ?: emptyMap()
//                    val docId = documentSnapshot.id
//                    val senderId = data["senderId"] as? String ?: ""
//                    val message = data["message"] as? String ?: ""
//                    allMessages.add(
//                        MessageModel(
//                            timeStamp = docId,
//                            senderId = senderId,
//                            message = message,
//                            friendName = friendName,
//                            friendImage = friendImage,
//                        )
//                    )
//                }
//            }
            val allMessages = querySnapshot.documents.map { documentSnapshot ->
                val data = documentSnapshot.data ?: emptyMap()
                val docId = documentSnapshot.id
                val senderId = data["senderId"] as? String ?: ""
                val message = data["message"] as? String ?: ""
                MessageModel(
                    timeStamp = docId,
                    senderId = senderId,
                    message = message,
                    friendName = friendName,
                    friendImage = friendImage,
                )
            }
            return allMessages
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun sendMessage(messageRequestModel: MessageRequestModel): CommonResponseModel {
        val timestamp = System.currentTimeMillis()
        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val createdAt = currentDateTime.format(formatter)
        val messageData = hashMapOf(
            "senderId" to messageRequestModel.userId,
            "message" to messageRequestModel.message,
            "createdAt" to createdAt
        )
        try {
            val result =
                FirebaseConfig().db.collection("Convos").document(messageRequestModel.convoId)
                    .collection("messages").document("$timestamp").set(messageData).await()
            return CommonResponseModel(success = true, message = result.toString())
        } catch (ex: Exception) {
            throw ex
        }
    }
}