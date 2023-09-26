package com.example.heartconnect.composables

import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext

@Composable
fun CustomToast(
    message: String?,
) {
    Toast.makeText(LocalContext.current, message ?: "Message", Toast.LENGTH_SHORT)
        .show()
}
