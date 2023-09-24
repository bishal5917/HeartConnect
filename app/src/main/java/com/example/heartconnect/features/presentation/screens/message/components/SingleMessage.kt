package com.example.heartconnect.features.presentation.screens.message.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.heartconnect.components.CustomNetworkImage
import com.example.heartconnect.components.CustomText
import com.example.heartconnect.features.data.models.message.MessageModel
import com.example.heartconnect.features.presentation.screens.splash.viewmodel.SplashViewModel
import com.example.heartconnect.ui.theme.HSizedBox1

@Composable
fun SingleMessage(messageModel: MessageModel) {
    val splashViewModel = hiltViewModel<SplashViewModel>()
    val userId = splashViewModel.userIdFlow.collectAsState()

    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement =
        if (userId.value == messageModel.senderId) Arrangement.End else Arrangement.Start
    ) {
        if (userId.value != messageModel.senderId) {
            CustomNetworkImage(
                imageUrl = messageModel.friendImage ?: "",
                modifier = Modifier.clip(CircleShape),
                parentmodifier = Modifier.size(30.dp)
            )
            HSizedBox1()
        }
        CustomText(
            data = messageModel.message ?: "",
            fontWeight = FontWeight.W400, fontSize = 16, maxLine = 5,
        )
    }
}