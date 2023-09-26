package com.example.heartconnect.composables

import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.heartconnect.core.navigation.Navigator
import com.example.heartconnect.ui.theme.Primary

@Composable
fun CustomAppbar(
    navController: NavController,
    title: String,
    actionButtonClicked: () -> Unit,
) {

    TopAppBar(backgroundColor = Color.White, title = {
        CustomText(data = title, fontSize = 15, fontWeight = FontWeight.W400)
    }, navigationIcon = {
        CustomIconButton(childIcon = Icons.Default.ArrowBack, contentDesc = "Back Icon") {
            Navigator().back(navController)
        }
    }, actions = {
        CustomIconButton(
            childIcon = Icons.Default.Info, contentDesc = "Info Icon", color = Primary
        ) {
            actionButtonClicked()
        }
    })
}