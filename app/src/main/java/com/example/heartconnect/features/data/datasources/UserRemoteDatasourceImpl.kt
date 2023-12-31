package com.example.heartconnect.features.data.datasources

import android.graphics.Bitmap
import com.example.heartconnect.features.data.models.chat.ChatRequestModel
import com.example.heartconnect.features.data.models.conversation.ConversationModel
import com.example.heartconnect.features.data.models.feed.FeedModel
import com.example.heartconnect.features.data.models.message.MessageModel
import com.example.heartconnect.features.data.models.message.MessageRequestModel
import com.example.heartconnect.core.configs.FirebaseConfig
import com.example.heartconnect.features.data.models.register.UserRegisterModel
import com.example.heartconnect.features.data.models.user.UserModel
import com.example.heartconnect.model.CommonRequestModel
import com.example.heartconnect.model.CommonResponseModel
import com.google.firebase.firestore.FieldPath
import com.google.firebase.storage.StorageMetadata
import com.google.type.DateTime
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UserRemoteDatasourceImpl : UserRemoteDatasource {
    override suspend fun getFeed(id: String): List<FeedModel> {
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
                    friendId = friendId,
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
                //new code

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

    override suspend fun createChat(chatRequestModel: ChatRequestModel): CommonResponseModel {
        var total = 0;
        try {
            val querySnapshot = FirebaseConfig().db.collection("Convos").get().await()
            querySnapshot.documents.map { documentSnapshot ->
                val data = documentSnapshot.data ?: emptyMap()
                val members = data["members"] as? List<String>
                if (members != null) {
                    if (members.contains(chatRequestModel.userId) && members.contains(
                            chatRequestModel.friendId
                        )
                    ) {
                        total += 1
                    }
                }
            }
            if (total == 0) {
                val chatCreateData = hashMapOf(
                    "members" to listOf(chatRequestModel.userId, chatRequestModel.friendId),
                )
                FirebaseConfig().db.collection("Convos").document().set(
                    chatCreateData
                ).await()
                return CommonResponseModel(success = true, message = "Chat Created")
            } else {
                return CommonResponseModel(success = true, message = "Already your chat")
            }
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun registerUser(userRegisterModel: UserRegisterModel): CommonResponseModel {
// Create metadata with the content type set to "image/jpeg"
        val metadata = StorageMetadata.Builder().setContentType("image/jpeg").build()
        val userMap = hashMapOf(
            "name" to userRegisterModel.name,
            "email" to userRegisterModel.email,
            "phone" to userRegisterModel.phone,
            "gender" to userRegisterModel.gender,
            "birthYear" to userRegisterModel.birthYear,
            "password" to userRegisterModel.password,
            "hobbies" to userRegisterModel.hobbies,
            "pics" to userRegisterModel.pics,
            "image" to "",
        )
        return try {
            val credential = FirebaseConfig().auth.createUserWithEmailAndPassword(
                userRegisterModel.email, userRegisterModel.password
            ).await()
            if (credential.user != null) {
                val newUser =
                    FirebaseConfig().db.collection("Users").document(credential.user!!.uid)
                        .set(userMap).await()
                val imageRef =
                    FirebaseConfig().storageRef.child("profiles/${System.currentTimeMillis()}")
                val uploadTask = imageRef.putFile(userRegisterModel.image, metadata).await()
                val downloadUrl = imageRef.downloadUrl.await()
                val imageSetMap = hashMapOf(
                    "image" to downloadUrl.toString()
                )
                val setImage = FirebaseConfig().db.collection("Users").document(
                    credential.user!!.uid
                ).update(imageSetMap as Map<String, Any>).await()
                CommonResponseModel(
                    success = true, message = "Registered", userId = credential.user!!.uid
                )
            } else {
                CommonResponseModel(success = false, message = "Error")
            }
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun sendResetMail(userModel: UserModel): CommonResponseModel {
        try {
            val result = FirebaseConfig().auth.sendPasswordResetEmail(userModel.email ?: "").await()
            return CommonResponseModel(success = true, message = result.toString())
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun getUserProfile(id: String): UserModel {
        try {
            val userSnapshot = FirebaseConfig().db.collection("Users").document(id).get().await()
            val data = userSnapshot.data
            return UserModel(
                name = data?.get("name") as String,
                birthYear = data["birthYear"] as String,
                hobbies = data["hobbies"] as List<String>,
                image = data["image"] as String,
                pics = data["pics"] as List<String>,
                email = data["email"] as String,
                phone = data["phone"] as String,
                gender = data["gender"] as String,
            )
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun changePicture(commonRequestModel: CommonRequestModel): CommonResponseModel {
        try {
            val imageRef = FirebaseConfig().imageRef
            val metadata = FirebaseConfig().storageMetadata
            val uploadTask = imageRef.putFile(commonRequestModel.image!!, metadata).await()
            val downloadUrl = imageRef.downloadUrl.await()
            val imageSetMap = hashMapOf(
                "image" to downloadUrl.toString()
            )
            FirebaseConfig().db.collection("Users").document(
                commonRequestModel.id!!
            ).update(imageSetMap as Map<String, Any>).await()
            return CommonResponseModel(success = true, message = "Picture changed successfully")
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun addPicture(commonRequestModel: CommonRequestModel): CommonResponseModel {
        try {
            val imageRef = FirebaseConfig().imageRef
            val metadata = FirebaseConfig().storageMetadata
            val uploadTask = imageRef.putFile(commonRequestModel.image!!, metadata).await()
            val downloadUrl = imageRef.downloadUrl.await()
            val userDocRef = FirebaseConfig().db.collection("Users").document(
                commonRequestModel.id ?: ""
            ).get().await()
            val images = userDocRef.get("pics") as List<String>?
            val updatedImages = images?.toMutableList()
            updatedImages?.add(downloadUrl.toString())
            FirebaseConfig().db.collection("Users").document(
                commonRequestModel.id ?: ""
            ).update("pics", updatedImages).await()
            return CommonResponseModel(success = true, message = "Imaged uploaded successfully")
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun getSingleFeed(commonRequestModel: CommonRequestModel): FeedModel {
        try {
            val querySnapshot =
                FirebaseConfig().db.collection("Users").document(commonRequestModel.id ?: "").get()
                    .await()
            val data = querySnapshot.data
            return FeedModel(
                name = data?.get("name") as String,
                birthYear = data["birthYear"] as String,
                hobbies = data["hobbies"] as List<String>,
                profileImage = data["image"] as String,
                pics = data["pics"] as List<String>,
                email = data["email"] as String,
                phone = data["phone"] as String,
                gender = data["gender"] as String,
            )
        } catch (ex: Exception) {
            throw ex
        }
    }
}