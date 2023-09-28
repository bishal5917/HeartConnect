package com.example.heartconnect.core.configs

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class FirebaseConfig {
    val db = FirebaseFirestore.getInstance()
    val auth: FirebaseAuth = Firebase.auth
    private val storage = FirebaseStorage.getInstance()
    val storageRef = storage.reference
}