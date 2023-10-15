package com.example.heartconnect.core.configs

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageMetadata

class FirebaseConfig {
    val db = FirebaseFirestore.getInstance()
    val auth: FirebaseAuth = Firebase.auth
    private val storage = FirebaseStorage.getInstance()
    val storageRef = storage.reference
    val imageRef =
        FirebaseConfig().storageRef.child("profiles/${System.currentTimeMillis()}")
    val storageMetadata = StorageMetadata.Builder().setContentType("image/jpeg").build()
}