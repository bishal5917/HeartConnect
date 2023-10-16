package com.example.heartconnect.features.presentation.screens.message.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.heartconnect.composables.CustomNetworkImage
import com.example.heartconnect.composables.CustomText
import com.example.heartconnect.features.data.models.message.MessageModel
import com.example.heartconnect.features.presentation.screens.splash.viewmodel.SplashViewModel
import com.example.heartconnect.ui.theme.HSizedBox1
import com.example.heartconnect.ui.theme.Primary

@Composable
fun SingleMessage(messageModel: MessageModel) {
    val splashViewModel = hiltViewModel<SplashViewModel>()
    val userId = splashViewModel.userIdFlow.collectAsState()

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if (userId.value == messageModel.senderId) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier.background(
                if (userId.value == messageModel.senderId) Primary else Color.LightGray,
                shape = RoundedCornerShape(6.dp)
            )
        ) {
            CustomText(
                modifier = Modifier.padding(6.dp),
                data = messageModel.message ?: "",
                fontWeight = FontWeight.W400,
                fontSize = 16,
                maxLine = 5,
                color = if (userId.value == messageModel.senderId) Color.White else Color.Black
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MessageCard() {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .background(Primary, shape = RoundedCornerShape(6.dp))
    ) {
        CustomText(
            modifier = Modifier.padding(6.dp),
            data = "OK everywone gu dnoit" ?: "",
            fontWeight = FontWeight.W400,
            fontSize = 16,
            maxLine = 5,
            color = Color.White
        )
    }
}