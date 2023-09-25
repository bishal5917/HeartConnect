package com.example.heartconnect.features.presentation.screens.chat.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.heartconnect.components.CustomNetworkImage
import com.example.heartconnect.components.CustomText
import com.example.heartconnect.features.data.models.conversation.ConversationModel
import com.example.heartconnect.navigation.AllScreen
import com.example.heartconnect.navigation.Navigation
import com.example.heartconnect.navigation.Navigator
import com.example.heartconnect.ui.theme.HSizedBox1
import com.example.heartconnect.ui.theme.VSizedBox0

@Composable
fun ConvoComponent(chatItem: ConversationModel, navController: NavController) {
    Box(modifier = Modifier.clickable {
        //execute function
//            onClick()
        if (chatItem.friendName != null)
            Navigator().navigateTo(
                navController,
                "${AllScreen.MessageScreen.name}/${chatItem.convoId}/${chatItem.friendName}"
            )
    }) {
        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            HSizedBox1()
            CustomNetworkImage(
                imageUrl = chatItem.friendImage ?: "",
                modifier = Modifier.clip(CircleShape),
                parentmodifier = Modifier.size(60.dp)
            )
            HSizedBox1()
            Column(verticalArrangement = Arrangement.SpaceBetween) {
                CustomText(
                    data = chatItem.friendName ?: "", fontWeight = FontWeight.W400, fontSize = 18
                )
                VSizedBox0()
                CustomText(
                    data = "Tap to chat ...",
                    fontWeight = FontWeight.W400,
                    fontSize = 12,
                    color = Color.LightGray
                )
            }
        }
    }

}