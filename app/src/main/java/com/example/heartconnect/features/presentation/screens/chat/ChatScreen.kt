package com.example.heartconnect.features.presentation.screens.chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.heartconnect.components.CustomCircularProgressIndicator
import com.example.heartconnect.features.data.models.feed.FeedModel
import com.example.heartconnect.features.presentation.screens.chat.components.ConvoComponent
import com.example.heartconnect.features.presentation.screens.home.components.HomeCard
import com.example.heartconnect.features.presentation.screens.home.viewmodel.HomeState
import com.example.heartconnect.ui.theme.VSizedBox1
import com.example.heartconnect.ui.theme.VSizedBox2

@Composable
fun ChatScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
    ) {
        Column {
            VSizedBox1()
            ConvoComponent()
            VSizedBox2()
            ConvoComponent()
            VSizedBox2()
            ConvoComponent()
            VSizedBox2()
            ConvoComponent()
            VSizedBox2()
            ConvoComponent()
            VSizedBox2()
        }
    }
}